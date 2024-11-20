package com.kino.movies.presentation.navigation.node

import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.kino.movies.presentation.favorite.FavoriteScreen
import com.kino.movies.presentation.favorite.FavoriteViewModel
import com.kino.movies.presentation.navigation.navHost.Screen
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.favoriteNode(
    navController: NavHostController, modifier: Modifier
) {
    composable(
        Screen.Favorite.route,
    ) {
        val viewModel = koinViewModel<FavoriteViewModel>()
        val viewState by viewModel.viewState.collectAsStateWithLifecycle()

        FavoriteScreen(
            favoriteViewState = viewState,
            onRefresh = viewModel::getFavoriteMovies,
            navToDetail = { movieId -> navController.navigate(Screen.Detail.withMovieId(movieId)) },
            modifier = modifier
        )
    }

}