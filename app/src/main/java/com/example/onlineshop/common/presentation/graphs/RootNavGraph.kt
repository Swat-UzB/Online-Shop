package com.example.onlineshop.common.presentation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.onlineshop.home.presentation.HomeScreen

/**
 * Created by Zayniddinov Ilyosjon on 18/03/2023.
 *  email zayniddinovilyos@gmail.com.
 */
@Composable
fun RootNavigationGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        route = Graph.ROOT,
        startDestination = Graph.AUTH,
    ) {
        authNavGraph(navHostController)

        composable(route = Graph.MAIN) {
            HomeScreen(
                goToSignIn = { navHostController.navigate(Graph.ROOT) { popUpTo(Graph.ROOT) } },
            )
        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val AUTH = "auth_graph"
    const val MAIN = "main_graph"
}