package com.kino.movies.presentation.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.kino.movies.presentation.designsystem.components.KinoBottomBar
import com.kino.movies.presentation.designsystem.components.KinoTopBar
import com.kino.movies.presentation.designsystem.components.KinoUiNotification
import com.kino.movies.presentation.utils.UiNotification
import com.kino.movies.presentation.utils.bottomBarDestinations

@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewState: HomeViewState,
    notification: UiNotification?,
    onRefresh: () -> Unit,
    navToDetail: (String) -> Unit,
) {
    val context = LocalContext.current
    val bottomBarDestinationList = bottomBarDestinations(context)
    val currentNavDestinationState = navController.currentBackStackEntryAsState().value?.destination
    var showNotificationDialog by remember { mutableStateOf(false) }

    LaunchedEffect(notification) {
        if (notification != null) {
            showNotificationDialog = true
        }
    }

    Scaffold(
        topBar = {
            KinoTopBar(context = context)
        },
        bottomBar = {
            KinoBottomBar(
                destinations = bottomBarDestinationList,
                currentNavDestinationState = currentNavDestinationState
            )
        },

        ) { padding ->

        HomeScreenContent(
            state = homeViewState,
            onEvent = onRefresh,
            toDetail = navToDetail,
            context = context,
            modifier = Modifier.padding(padding)
        )
    }

    if (showNotificationDialog) {
        KinoUiNotification (
            notification = notification!!,
            onDismiss = {
                showNotificationDialog = false
            })
    }

}
