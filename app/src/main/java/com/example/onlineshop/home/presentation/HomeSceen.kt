package com.example.onlineshop.home.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.onlineshop.R
import com.example.onlineshop.common.presentation.graphs.BottomBarScreen
import com.example.onlineshop.common.presentation.graphs.MainGraph
import com.google.accompanist.systemuicontroller.rememberSystemUiController

/**
 * Created by Zayniddinov Ilyosjon on 18/03/2023.
 *  email zayniddinovilyos@gmail.com.
 */


@Composable
fun HomeScreen(
    rootNavController: NavHostController,
    navController: NavHostController = rememberNavController(),
) {
    var title by remember { mutableStateOf("") }
    val systemUiController = rememberSystemUiController()
    val scaffoldState = rememberScaffoldState()
    SideEffect {
        systemUiController.setNavigationBarColor(
            color = Color.White,
            darkIcons = false
        )
    }
    Scaffold(scaffoldState = scaffoldState,
        bottomBar = { BottomNav(navControl = navController, title = { title = it }) },
        topBar = { TopBar(title) })
    { innerPaddings ->
        MainGraph(
            rootNavController = rootNavController,
            navController = navController,
            modifier = Modifier.padding(innerPaddings)
        )
    }
}

@Composable
fun BottomNav(navControl: NavHostController, title: (String) -> Unit) {
    val navBackStackEntry by navControl.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val bottomBarDestination = screens.any { it.route == currentRoute }
    if (bottomBarDestination) {
        CustomBottomNavigation(
            backgroundColor = Color.White,
            modifier = Modifier
                .clip(
                    RoundedCornerShape(
                        topStart = 24.dp,
                        topEnd = 24.dp
                    )
                )
        ) {
            screens.forEach { screen ->
                val selected = currentRoute == screen.route
                val iconColor = if (selected) {
                    title(screen.title)
                    colorResource(id = R.color.category_back)
                } else Color.White
                BottomNavigationItem(
                    selected = selected,
                    selectedContentColor = colorResource(id = R.color.icon_selected),
                    unselectedContentColor = colorResource(id = R.color.icon_unselected),
                    icon = {
                        Icon(
                            painter = painterResource(id = screen.resId),
                            modifier = Modifier
                                .background(
                                    color = iconColor,
                                    CircleShape
                                )
                                .size(44.dp)
                                .padding(12.dp),
                            contentDescription = "icon for navigation item"
                        )
                    },
                    onClick = {
                        if (currentRoute != screen.route && screen.route !in listOf(
                                "message",
                                "saved",
                                "card"
                            )
                        ) {
                            navControl.navigate(screen.route) {
                                popUpTo(navControl.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    })
            }
        }
    }
}

@Composable
fun CustomBottomNavigation(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colors.primarySurface,
    contentColor: Color = contentColorFor(backgroundColor),
    elevation: Dp = BottomNavigationDefaults.Elevation,
    content: @Composable RowScope.() -> Unit
) {
    Surface(
        color = backgroundColor,
        contentColor = contentColor,
        elevation = elevation,
        modifier = modifier
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .height(64.dp)
                .selectableGroup(),
            horizontalArrangement = Arrangement.SpaceBetween,
            content = content
        )
    }
}

@Composable
fun TopBar(title: String) {
    TopAppBar(
        elevation = 0.dp,
        title = {
            Text(
                text = title,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        },
        navigationIcon = {
            IconButton(onClick = { }, content = {
                when (title) {
                    screens.first().title -> Icon(
                        painterResource(id = R.drawable.home),
                        modifier = Modifier.size(24.dp),
                        contentDescription = null
                    )
                    !in screens.map { it.title } ->
                        Icon(
                            Icons.Default.ArrowBackIos,
                            contentDescription = null
                        )
                    else -> Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = null
                    )
                }
            })
        },
        actions = {
            if (title == screens.first().title) {
                Image(
                    painterResource(id = R.drawable.avatar),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .padding(end = 48.dp)
                        .size(56.dp)
                        .clip(CircleShape)
                        .align(Alignment.CenterVertically)
                )
            } else {
                Spacer(modifier = Modifier.width(64.dp))
            }
        },
        backgroundColor = Color.Transparent
    )
}

val screens = listOf(
    BottomBarScreen.Page1Screen,
    BottomBarScreen.SavedScreen,
    BottomBarScreen.CardScreen,
    BottomBarScreen.MessageScreen,
    BottomBarScreen.ProfileScreen
)