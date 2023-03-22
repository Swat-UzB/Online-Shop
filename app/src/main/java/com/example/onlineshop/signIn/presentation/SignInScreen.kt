package com.example.onlineshop.signIn.presentation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.onlineshop.R
import com.example.onlineshop.common.data.cache.model.User
import com.example.onlineshop.common.presentation.CommonButton
import com.example.onlineshop.common.presentation.EditField
import com.example.onlineshop.common.presentation.graphs.AuthScreen
import com.example.onlineshop.common.presentation.graphs.Graph
import com.example.onlineshop.common.presentation.theme.OnlineShopTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Created by Zayniddinov Ilyosjon on 13/03/2023.
 *  email zayniddinovilyos@gmail.com.
 */

@Composable
fun SignInMainScreen(
    navController: NavController,
    viewModel: SignInViewModel = hiltViewModel(),
    scope: CoroutineScope = rememberCoroutineScope()

) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    val state by viewModel.state.collectAsState()
    val isValidEmail: Boolean = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    val isAllFieldsFilled = firstName.isNotBlank() && lastName.isNotBlank() && isValidEmail
    @StringRes val errorMsg =
        if (!isValidEmail) R.string.not_valid_error_msg
        else R.string.email_already_exist
    val addUser = {
        scope.launch {
            viewModel.isEmailExist(
                User(firstName = firstName, lastName = lastName, email = email)
            )
        }
    }
    val focusManger = LocalFocusManager.current
    if (state.isUserCreated) {
        navController.popBackStack()
        navController.navigate(Graph.MAIN) }
    Column(modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(space = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.fillMaxHeight(0.2f))
        Text(text = stringResource(R.string.sign_in), fontWeight = FontWeight.W600, style = MaterialTheme.typography.h1)
        Spacer(modifier = Modifier.height(32.dp))
        EditField(placeholder = R.string.first_name, value = firstName, onValueChange = { firstName = it },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = { focusManger.moveFocus(FocusDirection.Down) })
        )

        EditField(placeholder = R.string.last_name, value = lastName, onValueChange = { lastName = it },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = { focusManger.moveFocus(FocusDirection.Down) })
        )

        EditField(
            placeholder = R.string.email,
            value = email,
            isError = !isValidEmail || state.isEmailExist,
            errorMsg = errorMsg,
            onValueChange = {
                email = it
                scope.launch { viewModel.disableEmailExistError() }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                focusManger.clearFocus()
                addUser()
            })
        )

        CommonButton(enabled = isAllFieldsFilled, butText = R.string.sign_in, onClick = { addUser() })

        ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
            val (already, log_in) = createRefs()
            Text(text = stringResource(id = R.string.already_have_an_account),
                color = Color.LightGray,
                fontSize = 11.sp,
                modifier = Modifier.constrainAs(already) {
                    baseline.linkTo(log_in.baseline)
                    end.linkTo(log_in.start, margin = 4.dp)
                })
            Text(text = stringResource(id = R.string.log_in),
                color = MaterialTheme.colors.primary,
                modifier = Modifier
                    .clickable { navController.navigate(AuthScreen.LoginMainScreen.route) }
                    .constrainAs(log_in) {
                        linkTo(parent.start, parent.end)
                    })
        }
        Spacer(modifier = Modifier.height(32.dp))
        ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
            val (apple, google) = createRefs()
            SignInWith(
                R.drawable.apple_logo,
                R.string.sign_in_with_apple,
                modifier = Modifier.constrainAs(apple) {
                    start.linkTo(google.start)
                    top.linkTo(google.bottom, margin = 32.dp)
                }
            )
            SignInWith(
                R.drawable.google_logo,
                R.string.sign_in_with_google,
                modifier = Modifier.constrainAs(google) {
                    linkTo(
                        parent.start,
                        parent.end,
                        bias = 0.5f
                    )
                })
        }


    }
}


//@DevicePreviews
@Preview(showBackground = true)
@Composable
fun DefaultPreviewMain() {
    OnlineShopTheme {
        val navController: NavHostController = rememberNavController()
        SignInMainScreen(navController = navController)
    }
}

@Composable
fun SignInWith(
    @DrawableRes icon: Int, @StringRes text: Int, modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = icon),
            modifier = Modifier.size(24.dp),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = stringResource(id = text), style = MaterialTheme.typography.body1
        )
    }
}