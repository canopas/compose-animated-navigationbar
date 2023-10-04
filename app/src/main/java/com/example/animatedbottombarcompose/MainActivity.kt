package com.example.animatedbottombarcompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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

                Scaffold(
                    bottomBar = {
                        AnimatedBottomBar(
                            selectedItem = selectedItem,
                            itemSize = 3,
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
                        composable(MainNavigation.ScreenD.route) {
                            Screen4(navigationItems)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Screen1(navigationItems: List<MainNavigation>) {
    var selectedItem by remember { mutableIntStateOf(0) }
    var indicatorDirection: IndicatorDirection by remember {
        mutableStateOf(IndicatorDirection.TOP)
    }
    var visibleItem: VisibleItem by remember {
        mutableStateOf(VisibleItem.ICON)
    }
    val directionOptions = listOf(IndicatorDirection.TOP, IndicatorDirection.BOTTOM)
    val visibilityOptions = listOf(VisibleItem.ICON, VisibleItem.LABEL, VisibleItem.BOTH)

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        Text(text = "Indicator Direction:")
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            directionOptions.forEach {
                RadioButton(selected = it == indicatorDirection, onClick = {
                    indicatorDirection = it
                })
                Text(text = it.name)
            }
        }

        Text(text = "Visible Item:")
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            visibilityOptions.forEach {
                RadioButton(selected = it == visibleItem, onClick = {
                    visibleItem = it
                })
                Text(text = it.name)
            }
        }

        Spacer(modifier = Modifier.height(100.dp))

        AnimatedBottomBar(
            selectedItem = selectedItem,
            itemSize = navigationItems.size,
            indicatorStyle = IndicatorStyle.LINE,
            indicatorDirection = indicatorDirection,
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
                    visibleItem = visibleItem
                )
            }
        }


        Spacer(modifier = Modifier.height(50.dp))

        AnimatedBottomBar(
            selectedItem = selectedItem,
            itemSize = navigationItems.size,
            indicatorStyle = IndicatorStyle.LINE,
            indicatorDirection = indicatorDirection
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
                    visibleItem = visibleItem
                )
            }
        }
    }
}

@Composable
fun Screen2(navigationItems: List<MainNavigation>) {
    var selectedItem by remember { mutableIntStateOf(0) }
    var itemStyle: ItemStyle by remember {
        mutableStateOf(ItemStyle.STYLE3)
    }
    val itemStyles = listOf(ItemStyle.STYLE1, ItemStyle.STYLE3)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        Spacer(modifier = Modifier.height(50.dp))

        Text(text = "Item Style:")
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            itemStyles.forEach {
                RadioButton(selected = it == itemStyle, onClick = {
                    itemStyle = it
                })
                Text(text = it.name)
            }
        }

        Spacer(modifier = Modifier.height(100.dp))

        AnimatedBottomBar(
            selectedItem = selectedItem,
            itemSize = navigationItems.size,
            indicatorStyle = IndicatorStyle.DOT,
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
                    visibleItem = VisibleItem.BOTH,
                    itemStyle = itemStyle
                )
            }
        }

        Spacer(modifier = Modifier.height(50.dp))

        AnimatedBottomBar(
            selectedItem = selectedItem,
            itemSize = navigationItems.size,
            indicatorStyle = IndicatorStyle.DOT,
            indicatorColor = Color.White
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
                    visibleItem = VisibleItem.BOTH,
                    itemStyle = itemStyle
                )
            }
        }
    }
}

@Composable
fun Screen3(navigationItems: List<MainNavigation>) {
    var selectedItem by remember { mutableIntStateOf(0) }
    var itemStyle: ItemStyle by remember {
        mutableStateOf(ItemStyle.STYLE3)
    }
    val itemStyles = listOf(ItemStyle.STYLE1, ItemStyle.STYLE2, ItemStyle.STYLE3)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        Spacer(modifier = Modifier.height(50.dp))

        Text(text = "Item Style:")

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            itemStyles.forEach {
                RadioButton(selected = it == itemStyle, onClick = {
                    itemStyle = it
                })
                Text(text = it.name)
            }
        }

        Spacer(modifier = Modifier.height(100.dp))

        AnimatedBottomBar(
            selectedItem = selectedItem,
            itemSize = navigationItems.size,
            indicatorStyle = if (itemStyle != ItemStyle.STYLE2) IndicatorStyle.FILLED else IndicatorStyle.NONE,
            containerColor = Color.Transparent,
            indicatorColor = Color.Red.copy(0.5f)
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
                    itemStyle = itemStyle,
                    activeIndicatorColor = Color.Red.copy(0.5f),
                )
            }
        }

        Spacer(modifier = Modifier.height(50.dp))

        AnimatedBottomBar(
            selectedItem = selectedItem,
            itemSize = navigationItems.size,
            indicatorStyle = if (itemStyle != ItemStyle.STYLE2) IndicatorStyle.FILLED else IndicatorStyle.NONE,
            indicatorColor = Color.White
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
                    itemStyle = itemStyle,
                    activeIndicatorColor = Color.White,
                )
            }
        }

        Spacer(modifier = Modifier.height(150.dp))
    }
}

@Composable
fun Screen4(navigationItems: List<MainNavigation>) {
    var selectedItem by remember { mutableIntStateOf(0) }
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

        Spacer(modifier = Modifier.height(150.dp))
    }
}