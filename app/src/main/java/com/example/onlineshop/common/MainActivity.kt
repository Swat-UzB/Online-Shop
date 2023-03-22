package com.example.onlineshop.common

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.onlineshop.common.presentation.graphs.RootNavigationGraph
import com.example.onlineshop.common.presentation.theme.OnlineShopTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OnlineShopTheme {
                // A surface container using the 'background' color from the theme
                RootNavigationGraph(navHostController = rememberNavController())
            }
        }
    }
}




