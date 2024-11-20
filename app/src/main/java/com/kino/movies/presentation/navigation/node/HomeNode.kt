package com.kino.movies.presentation.navigation.node

import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.kino.movies.presentation.home.HomeScreen
import com.kino.movies.presentation.home.HomeViewModel
import com.kino.movies.presentation.navigation.navHost.Screen
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.homeNode(
    navController: NavHostController, modifier: Modifier
) {
    composable(Screen.Home.route) {
        val viewModel = koinViewModel<HomeViewModel>()

        val viewState by viewModel.viewState.collectAsStateWithLifecycle()
        HomeScreen(
            viewState,
            onRefresh = viewModel::searchMovies,
            navToDetail = { movieId -> navController.navigate(Screen.Detail.withMovieId(movieId)) },
            modifier = modifier
        )
    }
}