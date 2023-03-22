package com.example.onlineshop.common.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController

//private val DarkColorPalette = darkColors(
//    primary = MainColor,
//    primaryVariant = Purple700,
//    secondary = Teal200,
//    background = MainBackground
//)

private val LightColorPalette = lightColors(
    primary = MainColor,
    primaryVariant = Purple700,
    secondary = Teal200,
    background = MainBackground

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun OnlineShopTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {

    val colors = LightColorPalette

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setNavigationBarColor(
            color = LightColorPalette.background ,
            darkIcons = false
        )
        systemUiController.setStatusBarColor(
            color = LightColorPalette.background,
            darkIcons = true
        )
    }
}