package com.example.onlineshop.profile

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.onlineshop.R
import com.example.onlineshop.common.presentation.graphs.Graph
import com.example.onlineshop.common.presentation.theme.montserrats
import com.example.onlineshop.login.presentation.LoginViewModel

/**
 * Created by Zayniddinov Ilyosjon on 17/03/2023.
 *  email zayniddinovilyos@gmail.com.
 */

@Composable
fun ProfileScreen(
    rootNavController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painterResource(id = R.drawable.avatar),
            null,
            Modifier
                .clip(CircleShape)
                .size(56.dp),
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center
        )
        CommonMontserratText(
            text = R.string.change_photo, fontWeight = FontWeight.W500, fontSize = 10.sp,
            color = colorResource(id = R.color.text_gray)
        )
        CommonMontserratText(text = R.string.owner_name, fontWeight = FontWeight.W700)
        Button(
            onClick = { }, shape = MaterialTheme.shapes.large, modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
                val (icon, text) = createRefs()
                Icon(
                    modifier = Modifier.constrainAs(icon) { linkTo(parent.start, text.start) },
                    painter = painterResource(id = R.drawable.upload_icon),
                    tint = colorResource(id = R.color.upload_icon_clr),
                    contentDescription = null
                )

                CommonMontserratText(
                    modifier = Modifier.constrainAs(text) {
                        linkTo(parent.start, parent.end)
                    },
                    text = R.string.upload_item,
                    color = colorResource(id = R.color.upload_icon_clr),
                    fontWeight = FontWeight.W600
                )
            }
        }
        ProfileCategory(label = R.string.trade_store)
        ProfileCategory(label = R.string.payment_method)
        ProfileCategory(label = R.string.balance, isArrowExist = false, isExistPrice = true)
        ProfileCategory(label = R.string.trade_history)
        ProfileCategory(icon = R.drawable.restore_purchase, label = R.string.restore_purchase)
        ProfileCategory(icon = R.drawable.help, label = R.string.help, isArrowExist = false)
        ProfileCategory(R.drawable.log_out, R.string.log_out, Modifier.clickable {
            rootNavController.navigate(Graph.ROOT) { popUpTo(Graph.ROOT) }
            viewModel.removeUserName()
        }, false)
    }
}

@Composable
fun ProfileCategory(
    @DrawableRes icon: Int = R.drawable.credit_card,
    @StringRes label: Int,
    modifier: Modifier = Modifier,
    isArrowExist: Boolean = true,
    isExistPrice: Boolean = false,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null, modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(colorResource(id = R.color.category_back))
                .padding(10.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        CommonMontserratText(
            text = label,
            fontSize = 16.sp
        )
        val customMod = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.End)
        if (isArrowExist) {
            Icon(
                Icons.Default.KeyboardArrowRight,
                contentDescription = null,
                modifier = customMod
            )
        }
        if (isExistPrice) {
            CommonMontserratText(
                text = R.string.price,
                modifier = customMod
            )
        }

    }
}


@Composable
fun CommonMontserratText(
    @StringRes text: Int,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.Normal,
    color: Color = Color.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified,
) {
    Text(
        color = color,
        text = stringResource(id = text),
        fontSize = fontSize,
        fontFamily = montserrats,
        fontWeight = fontWeight,
        modifier = modifier

    )

}