package com.kino.movies.presentation.utils

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.kino.movies.R
import com.kino.movies.presentation.Screen


data class BottomBarDestination(
    val icon: ImageVector,
    val route: String,
    val title: String,
)

val bottomBarDestinations: (context: Context) -> List<BottomBarDestination> = { context ->
    listOf(
        BottomBarDestination(
            icon = Icons.Default.Home,
            route = Screen.Home.route,
            title = context.resources.getString(R.string.home)
        ),
        BottomBarDestination(
            icon = Icons.Default.Star,
            route = Screen.Favorite.route,
            title = context.resources.getString(R.string.favorite)
        ),
        BottomBarDestination(
            icon = Icons.Default.Settings,
            route = Screen.Settings.route,
            title = context.resources.getString(R.string.settings)
        )
    )
}