package com.example.onlineshop.common.presentation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.onlineshop.login.presentation.LoginMainScreen
import com.example.onlineshop.signIn.presentation.SignInMainScreen

/**
 * Created by Zayniddinov Ilyosjon on 18/03/2023.
 *  email zayniddinovilyos@gmail.com.
 */
fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.AUTH,
        startDestination = AuthScreen.SignInMainScreen.route,

        ) {
        composable(route = AuthScreen.SignInMainScreen.route) {
            SignInMainScreen(navController = navController)
        }
        composable(route = AuthScreen.LoginMainScreen.route) {
            LoginMainScreen(navController = navController)
        }
    }
}

sealed class AuthScreen(val route: String) {
    object LoginMainScreen : AuthScreen(route = "login")
    object SignInMainScreen : AuthScreen(route = "sign_in")
}