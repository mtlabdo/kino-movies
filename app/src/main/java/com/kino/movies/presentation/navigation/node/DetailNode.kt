package com.kino.movies.presentation.navigation.node

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.kino.movies.presentation.detail.DetailScreen
import com.kino.movies.presentation.detail.DetailViewModel
import com.kino.movies.presentation.navigation.navHost.DETAIL_MOVIE_ARGUMENT_KEY
import com.kino.movies.presentation.navigation.navHost.Screen
import org.koin.androidx.compose.koinViewModel


fun NavGraphBuilder.detailNode(
    navController: NavHostController,
    modifier: Modifier
) {
    composable(Screen.Detail.route, arguments = listOf(navArgument(DETAIL_MOVIE_ARGUMENT_KEY) {
        type = NavType.StringType
    })) { backStackEntry ->
        backStackEntry.arguments?.getString(DETAIL_MOVIE_ARGUMENT_KEY)?.let { movieId ->
            val viewModel = koinViewModel<DetailViewModel>()

            LaunchedEffect(Unit) {
                viewModel.getMovieDetail(movieId)
            }

            val viewState by viewModel.viewState.collectAsStateWithLifecycle()

            DetailScreen(
                viewState = viewState,
                onBack = { navController.popBackStack() },
                onRefresh = { viewModel.getMovieDetail(movieId) },
                onUpdateFavorite = { newFavState ->
                    viewModel.updateFavoriteOfMovie(movieId, newFavState)
                },
            )
        }
    }
}