package com.kino.movies.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kino.movies.R
import com.kino.movies.presentation.designsystem.components.KinoBottomBar
import com.kino.movies.presentation.designsystem.components.KinoTopBar
import com.kino.movies.presentation.navigation.KinomoviesNavHost
import com.kino.movies.presentation.utils.bottomBarDestinations
import com.kino.movies.presentation.utils.routesWithBottomBar

@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val showBars = remember { mutableStateOf(true) }

    val titleRes = bottomBarDestinations.find { it.route == currentRoute }?.title


    Scaffold(
        topBar = {
            KinoAnimateVisibility(visible = showBars.value) {
                KinoTopBar(
                    title = stringResource(id = titleRes ?: R.string.app_name)
                )
            }
        },
        bottomBar = {
            KinoAnimateVisibility(visible = showBars.value) {
                KinoBottomBar(
                    destinations = bottomBarDestinations,
                    currentRoute = currentRoute,
                    onNavigateToDestination = { bottomBarDestination ->
                        navController.navigate(bottomBarDestination.route) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    }
                )
            }
        },

        ) { padding ->
        KinomoviesNavHost(navController, modifier = Modifier.padding(padding))
    }
}

@Composable
fun KinoAnimateVisibility(
    visible: Boolean = true,
    content: @Composable () -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        content()
    }
}