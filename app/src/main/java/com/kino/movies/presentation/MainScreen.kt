package com.kino.movies.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kino.movies.presentation.designsystem.component.KinoBottomBar
import com.kino.movies.presentation.designsystem.component.KinoTopBar
import com.kino.movies.presentation.navigation.KinomoviesNavHost
import com.kino.movies.presentation.utils.bottomBarDestinations
import com.kino.movies.presentation.utils.bottomBarSetRoutes

const val NO_RESOURCE = 0
@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val showBars = remember { mutableStateOf(false) }
    var titleRes by remember { mutableStateOf(NO_RESOURCE) }

    LaunchedEffect(currentRoute) {
        showBars.value = currentRoute in bottomBarSetRoutes
        bottomBarDestinations.find { it.route == currentRoute }?.title?.let { resTitle ->
            titleRes = resTitle
        }
    }

    Scaffold(
        topBar = {
            KinoAnimateVisibility(visible = showBars.value)
            {
                KinoTopBar(
                    title = if (titleRes != NO_RESOURCE) stringResource(id = titleRes) else ""
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