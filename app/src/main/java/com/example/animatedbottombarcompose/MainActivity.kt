package com.example.animatedbottombarcompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.animatedbottombarcompose.ui.theme.AnimatedBottomBarComposeTheme
import com.example.bottombar.BottomBarWithTabIndicator
import com.example.bottombar.components.IconTextAnimator
import com.example.bottombar.model.MainNavigation

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
                        BottomBarWithTabIndicator(
                            selectedItem = selectedItem,
                            itemSize = navigationItems.size
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