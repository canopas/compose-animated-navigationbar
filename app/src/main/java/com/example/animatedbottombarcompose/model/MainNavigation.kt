package com.example.animatedbottombarcompose.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.ui.graphics.vector.ImageVector

sealed class MainNavigation(
    val title: String,
    val route: String,
    val icon: ImageVector
) {
    data object ScreenA : MainNavigation(
        title = "Home",
        route = "HomeScreen",
        icon = Icons.Default.Home
    )

    data object ScreenB : MainNavigation(
        title = "Notifications",
        route = "NotificationsScreen",
        icon = Icons.Default.Notifications
    )

    data object ScreenC : MainNavigation(
        title = "Favourite",
        route = "FavouriteScreen",
        icon = Icons.Default.Favorite
    )

    data object ScreenD : MainNavigation(
        title = "Emails",
        route = "EmailScreen",
        icon = Icons.Default.Email
    )
}