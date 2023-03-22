package com.example.onlineshop.login.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.onlineshop.R
import com.example.onlineshop.common.presentation.CommonButton
import com.example.onlineshop.common.presentation.EditField
import com.example.onlineshop.common.presentation.graphs.Graph
import com.example.onlineshop.common.presentation.theme.OnlineShopTheme
import com.example.onlineshop.profile.CommonMontserratText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Created by Zayniddinov Ilyosjon on 16/03/2023.
 *  email zayniddinovilyos@gmail.com.
 */

@Composable
fun LoginMainScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel(),
    scope: CoroutineScope = rememberCoroutineScope()
) {
    val state by viewModel.state.collectAsState()
    var firstName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    val focusManger = LocalFocusManager.current
    val checkUser = { scope.launch { viewModel.isUserExist(firstName) } }
    val isFieldsNotBlank = password.isNotBlank() && firstName.isNotBlank()
    if (state.isUserExist) {
        with(navController) {
            popBackStack()
            navigate(Graph.MAIN)
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(space = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.fillMaxHeight(0.2f))
        Text(
            text = stringResource(R.string.welcome_back),
            fontWeight = FontWeight.W600,
            style = MaterialTheme.typography.h1
        )
        Spacer(modifier = Modifier.height(32.dp))
        EditField(
            placeholder = R.string.first_name, value = firstName,
            onValueChange = {
                firstName = it
                viewModel.hideError()
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = { focusManger.moveFocus(FocusDirection.Down) })
        )

        EditField(
            value = password, onValueChange = { password = it }, placeholder = R.string.psw,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password, imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                focusManger.clearFocus()
                checkUser()
            }),
            visualTransformation = if (passwordVisibility) VisualTransformation.None
            else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (passwordVisibility) Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff
                val desc = if (passwordVisibility) R.string.hide_psw
                else R.string.show_psw
                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(image, contentDescription = stringResource(id = desc))
                }
            }
        )
        Spacer(modifier = Modifier.height(58.dp))
        CommonButton(
            butText = R.string.log_in,
            onClick = { checkUser() },
            enabled = isFieldsNotBlank
        )
        if (state.showErrorUserNotExist) {
            CommonMontserratText(
                text = R.string.invalid_login,
                color = colorResource(id = R.color.error_color),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewLogin() {
    OnlineShopTheme {
        val navController: NavHostController = rememberNavController()
        LoginMainScreen(navController = navController)
    }
}
