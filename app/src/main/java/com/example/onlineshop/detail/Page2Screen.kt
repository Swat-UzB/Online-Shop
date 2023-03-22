package com.example.onlineshop.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * Created by Zayniddinov Ilyosjon on 17/03/2023.
 *  email zayniddinovilyos@gmail.com.
 */
@Composable
fun Page2MainScreen() {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Page2MainScreen")
    }
}