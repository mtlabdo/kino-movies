package com.kino.movies.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kino.movies.presentation.designsystem.component.KinoBottomBar
import com.kino.movies.presentation.designsystem.component.KinoTopBar
import com.kino.movies.presentation.designsystem.component.alertDialog.AlertDialogState
import com.kino.movies.presentation.designsystem.component.alertDialog.KinoAlertDialog
import com.kino.movies.presentation.navigation.navHost.KinomoviesNavHost
import com.kino.movies.presentation.utils.ObserveAsEvents
import com.kino.movies.presentation.utils.UiNotification
import com.kino.movies.presentation.utils.UiNotificationController
import com.kino.movies.presentation.utils.bottomBarDestinations
import com.kino.movies.presentation.utils.bottomBarSetRoutes
import com.kino.movies.presentation.utils.getString
import kotlinx.coroutines.launch

const val NO_RESOURCE = 0

@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val showBars = remember { mutableStateOf(false) }
    var titleRes by remember { mutableStateOf(NO_RESOURCE) }

    val context = LocalContext.current

    LaunchedEffect(currentRoute) {
        showBars.value = currentRoute in bottomBarSetRoutes
        bottomBarDestinations.find { it.route == currentRoute }?.title?.let { resTitle ->
            titleRes = resTitle
        }
    }


    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val dialogState = remember { AlertDialogState() }

    ObserveAsEvents(UiNotificationController.notification) { event ->
        scope.launch {
            if (event is UiNotification.DialogNotificationEvent) {
                dialogState.showDialog(event)
            } else if (event is UiNotification.SnackBarNotificationEvent) {
                snackbarHostState.currentSnackbarData?.dismiss()
                val result = snackbarHostState.showSnackbar(
                    message = event.message.getString(context = context),
                    actionLabel = event.actionLabel?.getString(context = context),
                    duration = event.duration,
                )
                if (result == SnackbarResult.ActionPerformed) {
                    event.action?.invoke()
                }
            }
        }

    }
    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
            )
        },

        topBar = {
            KinoAnimateVisibility(visible = showBars.value) {
                KinoTopBar(
                    title = if (titleRes != NO_RESOURCE) stringResource(id = titleRes) else ""
                )
            }
        },
        bottomBar = {
            KinoAnimateVisibility(visible = showBars.value) {
                KinoBottomBar(destinations = bottomBarDestinations,
                    currentRoute = currentRoute,
                    onNavigateToDestination = { bottomBarDestination ->
                        navController.navigate(bottomBarDestination.route) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    })
            }
        },
    ) { padding ->
        KinoAlertDialog(dialogState)
        KinomoviesNavHost(navController, modifier = Modifier.padding(padding))
    }
}


@Composable
fun KinoAnimateVisibility(
    visible: Boolean = true, content: @Composable () -> Unit
) {
    AnimatedVisibility(
        visible = visible, enter = fadeIn(), exit = fadeOut()
    ) {
        content()
    }
}