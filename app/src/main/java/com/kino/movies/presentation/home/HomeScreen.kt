package com.kino.movies.presentation.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.kino.movies.presentation.designsystem.components.KinoBottomBar
import com.kino.movies.presentation.designsystem.components.KinoTopBar
import com.kino.movies.presentation.utils.bottomBarDestinations

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel,
    navToDetail: (String) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val bottomBarDestinationList = bottomBarDestinations(context)
    val currentNavDestinationState = navController.currentBackStackEntryAsState().value?.destination

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
            state = state,
            onEvent = viewModel::searchMovies,
            toDetail = navToDetail,
            context = context,
            modifier = Modifier.padding(padding)
        )
    }

}
