package com.example.animatedbottombarcompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.animatedbottombarcompose.composables.Screen1
import com.example.animatedbottombarcompose.composables.Screen2
import com.example.animatedbottombarcompose.composables.Screen3
import com.example.animatedbottombarcompose.model.MainNavigation
import com.example.animatedbottombarcompose.ui.theme.AnimatedBottomBarComposeTheme
import com.example.bottombar.AnimatedBottomBar
import com.example.bottombar.components.BottomBarItem
import com.example.bottombar.model.IndicatorStyle

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

                Scaffold(
                    bottomBar = {
                        AnimatedBottomBar(
                            selectedItem = selectedItem,
                            itemSize = navigationItems.take(3).size,
                            containerColor = Color.LightGray,
                            indicatorStyle = IndicatorStyle.LINE
                        ) {
                            navigationItems.take(3).forEachIndexed { index, navigationItem ->
                                BottomBarItem(
                                    selected = currentRoute == navigationItem.route,
                                    onClick = {
                                        if (currentRoute != navigationItem.route) {
                                            selectedItem = index
                                            navController.popBackStack()
                                            navController.navigate(navigationItem.route) {
                                                navController.graph.startDestinationRoute?.let { route ->
                                                    popUpTo(route) {
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
                                    containerColor = Color.Transparent
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
                            Screen1(navigationItems)
                        }
                        composable(MainNavigation.ScreenB.route) {
                            Screen2(navigationItems)
                        }
                        composable(MainNavigation.ScreenC.route) {
                            Screen3(navigationItems)
                        }
                    }
                }
            }
        }
    }
}