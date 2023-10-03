package com.example.animatedbottombarcompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.animatedbottombarcompose.ui.theme.AnimatedBottomBarComposeTheme
import com.example.bottombar.AnimatedBottomBar
import com.example.bottombar.components.BottomBarItem
import com.example.animatedbottombarcompose.model.MainNavigation
import com.example.bottombar.model.IndicatorDirection
import com.example.bottombar.model.IndicatorStyle
import com.example.bottombar.model.ItemStyle
import com.example.bottombar.model.VisibleItem

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimatedBottomBarComposeTheme {

                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                val navigationItems = MainNavigation::class.nestedClasses.map {
                    it.objectInstance as MainNavigation
                }
                var selectedItem by remember { mutableIntStateOf(0) }

                /*Scaffold(
                    bottomBar = {
                        BottomBarWithTabIndicator(
                            selectedItem = selectedItem,
                            itemSize = navigationItems.size,
                            indicatorStyle = IndicatorStyle.LINE
                        ) {
                            navigationItems.forEachIndexed { index, navigationItem ->
                                IconTextAnimator(
                                    selected = currentRoute == navigationItem.route,
                                    onClick = {
                                        if (currentRoute != navigationItem.route) {
                                            selectedItem = index
                                            navController.popBackStack()
                                            navController.navigate(navigationItem.route) {
                                                navController.graph.startDestinationRoute?.let { screen_route ->
                                                    popUpTo(screen_route) {
                                                        saveState = true
                                                    }
                                                }
                                                launchSingleTop = true
                                                restoreState = true
                                            }
                                        }
                                    },
                                    imageVector = navigationItem.icon,
                                    label = navigationItem.title
                                )
                            }
                        }
                        BottomBarWithTabIndicator(
                            selectedItem = selectedItem,
                            itemSize = navigationItems.size
                        ) {
                            navigationItems.forEachIndexed { index, navigationItem ->
                                val selected = currentRoute == navigationItem.route
                                BottomBarItem(
                                    selected = selected,
                                    onClick = {
                                        if (currentRoute != navigationItem.route) {
                                            selectedItem = index
                                            navController.popBackStack()
                                            navController.navigate(navigationItem.route) {
                                                navController.graph.startDestinationRoute?.let { screen_route ->
                                                    popUpTo(screen_route) {
                                                        saveState = true
                                                    }
                                                }
                                                launchSingleTop = true
                                                restoreState = true
                                            }
                                        }
                                    },
                                    imageVector = navigationItem.icon,
                                    label = navigationItem.title,
                                    indicatorStyle = IndicatorStyle.FILLED,
                                    iconColor = if (selected) Color.White else Color.Black,
                                    textColor = if (selected) Color.White else Color.Black,
                                )
                            }
                        }
                    }
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = MainNavigation.ScreenA.route
                    ) {
                        composable(MainNavigation.ScreenA.route) {
                            HomeScreen()
                        }
                        composable(MainNavigation.ScreenB.route) {
                            NotificationsScreen()
                        }
                        composable(MainNavigation.ScreenC.route) {
                            FavouritesScreen()
                        }
                        composable(MainNavigation.ScreenD.route) {
                            EmailsScreen()
                        }
                    }
                }*/

                Scaffold {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ) {

                        Spacer(modifier = Modifier.height(50.dp))

                        AnimatedBottomBar(
                            selectedItem = selectedItem,
                            itemSize = navigationItems.size,
                            indicatorStyle = IndicatorStyle.LINE
                        ) {
                            navigationItems.forEachIndexed { index, navigationItem ->
                                val selected = index == selectedItem
                                BottomBarItem(
                                    selected = selected,
                                    onClick = {
                                        selectedItem = index
                                    },
                                    imageVector = navigationItem.icon,
                                    label = navigationItem.title,
                                    iconColor = if (selected) Color.White else Color.Black,
                                    textColor = if (selected) Color.White else Color.Black
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(50.dp))

                        AnimatedBottomBar(
                            selectedItem = selectedItem,
                            itemSize = navigationItems.size,
                            indicatorStyle = IndicatorStyle.LINE,
                            indicatorDirection = IndicatorDirection.BOTTOM
                        ) {
                            navigationItems.forEachIndexed { index, navigationItem ->
                                val selected = index == selectedItem
                                BottomBarItem(
                                    selected = selected,
                                    onClick = {
                                        selectedItem = index
                                    },
                                    imageVector = navigationItem.icon,
                                    label = navigationItem.title,
                                    iconColor = if (selected) Color.White else Color.Black,
                                    textColor = if (selected) Color.White else Color.Black,
                                    visibleItem = VisibleItem.BOTH
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(50.dp))

                        AnimatedBottomBar(
                            selectedItem = selectedItem,
                            itemSize = navigationItems.size,
                            indicatorStyle = IndicatorStyle.LINE,
                            indicatorDirection = IndicatorDirection.BOTTOM,
                            containerColor = Color.Transparent,
                            indicatorColor = Color.Red
                        ) {
                            navigationItems.forEachIndexed { index, navigationItem ->
                                val selected = index == selectedItem
                                BottomBarItem(
                                    selected = selected,
                                    onClick = {
                                        selectedItem = index
                                    },
                                    imageVector = navigationItem.icon,
                                    label = navigationItem.title,
                                    iconColor = if (selected) Color.Red else Color.Black,
                                    textColor = if (selected) Color.Red else Color.Black,
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(50.dp))

                        AnimatedBottomBar(
                            selectedItem = selectedItem,
                            itemSize = navigationItems.size,
                            indicatorStyle = IndicatorStyle.DOT
                        ) {
                            navigationItems.forEachIndexed { index, navigationItem ->
                                val selected = index == selectedItem
                                BottomBarItem(
                                    selected = selected,
                                    onClick = {
                                        selectedItem = index
                                    },
                                    imageVector = navigationItem.icon,
                                    label = navigationItem.title,
                                    iconColor = if (selected) Color.White else Color.Black,
                                    textColor = if (selected) Color.White else Color.Black,
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(50.dp))

                        AnimatedBottomBar(
                            selectedItem = selectedItem,
                            itemSize = navigationItems.size,
                            indicatorStyle = IndicatorStyle.DOT
                        ) {
                            navigationItems.forEachIndexed { index, navigationItem ->
                                val selected = index == selectedItem
                                BottomBarItem(
                                    selected = selected,
                                    onClick = {
                                        selectedItem = index
                                    },
                                    imageVector = navigationItem.icon,
                                    label = navigationItem.title,
                                    iconColor = if (selected) Color.White else Color.Black,
                                    textColor = if (selected) Color.White else Color.Black,
                                    itemStyle = ItemStyle.STYLE3,
                                    activeIndicatorColor = Color.White,
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(50.dp))

                        AnimatedBottomBar(
                            selectedItem = selectedItem,
                            itemSize = navigationItems.size,
                            indicatorStyle = IndicatorStyle.NONE
                        ) {
                            navigationItems.forEachIndexed { index, navigationItem ->
                                val selected = index == selectedItem
                                BottomBarItem(
                                    selected = selected,
                                    onClick = {
                                        selectedItem = index
                                    },
                                    imageVector = navigationItem.icon,
                                    label = navigationItem.title,
                                    iconColor = if (selected) Color.Black else Color.White,
                                    textColor = if (selected) Color.Black else Color.White,
                                    itemStyle = ItemStyle.STYLE2,
                                    activeIndicatorColor = Color.White,
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(50.dp))

                        AnimatedBottomBar(
                            selectedItem = selectedItem,
                            itemSize = navigationItems.size,
                            indicatorStyle = IndicatorStyle.FILLED
                        ) {
                            navigationItems.forEachIndexed { index, navigationItem ->
                                val selected = index == selectedItem
                                BottomBarItem(
                                    selected = selected,
                                    onClick = {
                                        selectedItem = index
                                    },
                                    imageVector = navigationItem.icon,
                                    label = navigationItem.title,
                                    iconColor = if (selected) Color.Black else Color.White,
                                    textColor = if (selected) Color.Black else Color.White,
                                    itemStyle = ItemStyle.STYLE3,
                                    activeIndicatorColor = Color.White,
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(50.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "HomeScreen")
    }
}

@Composable
fun NotificationsScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "NotificationsScreen")
    }
}

@Composable
fun FavouritesScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "FavouritesScreen")
    }
}

@Composable
fun EmailsScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "EmailsScreen")
    }
}