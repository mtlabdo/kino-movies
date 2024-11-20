package com.kino.movies.presentation.navigation.node

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.kino.movies.presentation.navigation.navHost.Screen
import com.kino.movies.presentation.setting.SettingScreen
import com.kino.movies.presentation.setting.SettingViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.settingNode(
    navController: NavHostController,
    modifier: Modifier
) {
    composable(
        Screen.Settings.route,
    ) {
        val viewModel = koinViewModel<SettingViewModel>()
        val viewState by viewModel.viewState.collectAsStateWithLifecycle()


        SettingScreen(
            settingViewState = viewState,
            modifier = modifier,
            onEvent = viewModel::onEvent
        )
    }
}
