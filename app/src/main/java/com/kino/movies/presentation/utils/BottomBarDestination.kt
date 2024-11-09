package com.kino.movies.presentation.utils

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.kino.movies.R
import com.kino.movies.presentation.navigation.Screen

data class BottomBarDestination(
    val icon: ImageVector,
    val route: String,
    @StringRes val title: Int,
)

val bottomBarDestinations =
    listOf(
        BottomBarDestination(
            icon = Icons.Default.Home,
            route = Screen.Home.route,
            title = R.string.home
        ),
        BottomBarDestination(
            icon = Icons.Default.Star,
            route = Screen.Favorite.route,
            title = R.string.favorite
        ),
        BottomBarDestination(
            icon = Icons.Default.Settings,
            route = Screen.Settings.route,
            title = R.string.settings
        )
    )


val bottomBarSetRoutes: Set<String> = bottomBarDestinations.map {
    it.route
}.toSet()