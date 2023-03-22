package com.example.onlineshop.common.presentation.graphs

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.onlineshop.R
import com.example.onlineshop.detail.Page2MainScreen
import com.example.onlineshop.home.presentation.Page1MainScreen
import com.example.onlineshop.profile.ProfileScreen


/**
 * Created by Zayniddinov Ilyosjon on 18/03/2023.
 *  email zayniddinovilyos@gmail.com.
 */

@Composable
fun MainGraph(
    goToSignIn: () -> Unit,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        route = Graph.MAIN,
        startDestination = BottomBarScreen.Page1Screen.route,
        modifier = modifier
    ) {
        composable(route = BottomBarScreen.Page1Screen.route) {
            Page1MainScreen()
        }
        composable(route = BottomBarScreen.Page2Screen.route) {
            Page2MainScreen()
        }
        composable(route = BottomBarScreen.ProfileScreen.route) {
            ProfileScreen(goToSignIn)
        }
    }
}


sealed class BottomBarScreen(
    val route: String,
    val title: String,
    @DrawableRes val resId: Int
) {
    object Page1Screen : BottomBarScreen("page1", "Trade by bata", resId = R.drawable.main)
    object Page2Screen : BottomBarScreen("page2", "", resId = R.drawable.main)
    object ProfileScreen : BottomBarScreen("profile", "Profile", resId = R.drawable.group)
    object MessageScreen : BottomBarScreen("message", "Message", resId = R.drawable.message)
    object SavedScreen : BottomBarScreen("saved", "Saved", resId = R.drawable.saved)
    object CardScreen : BottomBarScreen("card", "Card", resId = R.drawable.cart)
}