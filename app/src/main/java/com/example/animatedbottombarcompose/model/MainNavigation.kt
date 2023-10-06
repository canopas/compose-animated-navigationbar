package com.example.animatedbottombarcompose.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.ui.graphics.vector.ImageVector

sealed class MainNavigation(
    val title: String,
    val route: String,
    val icon: ImageVector
) {
    data object ScreenA : MainNavigation(
        title = "Screen1",
        route = "Screen1",
        icon = Icons.Outlined.Home
    )

    data object ScreenB : MainNavigation(
        title = "Screen2",
        route = "Screen2",
        icon = Icons.Outlined.Notifications
    )

    data object ScreenC : MainNavigation(
        title = "Screen3",
        route = "Screen3",
        icon = Icons.Outlined.FavoriteBorder
    )

    data object ScreenD : MainNavigation(
        title = "Screen4",
        route = "Screen4",
        icon = Icons.Outlined.Email
    )
}