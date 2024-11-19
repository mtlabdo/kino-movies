package com.kino.movies.presentation.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.kino.movies.presentation.detail.DetailScreen
import com.kino.movies.presentation.detail.DetailViewModel
import com.kino.movies.presentation.favorite.FavoriteScreen
import com.kino.movies.presentation.favorite.FavoriteViewModel
import com.kino.movies.presentation.home.HomeScreen
import com.kino.movies.presentation.home.HomeViewModel
import com.kino.movies.presentation.setting.SettingScreen
import com.kino.movies.presentation.setting.SettingViewModel
import com.kino.movies.presentation.utils.UiNotification
import org.koin.androidx.compose.koinViewModel

@Composable
fun KinomoviesNavHost(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        homeNode(navController, modifier)
        favoriteNode(navController, modifier)
        settingNode(navController, modifier)
        detailNode(navController)
    }
}

fun NavGraphBuilder.homeNode(
    navController: NavHostController,
    modifier: Modifier
) {
    composable(Screen.Home.route) {
        val viewModel = koinViewModel<HomeViewModel>()

        var uiNotification by remember {
            mutableStateOf<UiNotification?>(null)
        }

        LaunchedEffect(Unit) {
            viewModel.notification.collect { notification ->
                uiNotification = notification
            }
        }


        val viewState by viewModel.viewState.collectAsStateWithLifecycle()
        HomeScreen(
            viewState,
            uiNotification,
            onRefresh = viewModel::searchMovies,
            navToDetail = { movieId -> navController.navigate(Screen.Detail.withMovieId(movieId)) },
            modifier = modifier
        )
    }
}

fun NavGraphBuilder.favoriteNode(
    navController: NavHostController,
    modifier: Modifier
) {
    composable(
        Screen.Favorite.route,
    ) {
        val viewModel = koinViewModel<FavoriteViewModel>()
        val viewState by viewModel.viewState.collectAsStateWithLifecycle()
        var uiNotification by remember {
            mutableStateOf<UiNotification?>(null)
        }
        LaunchedEffect(Unit) {
            viewModel.notification.collect { notification ->
                Log.d("FavoriteViewModel", "Notification 1 received: $notification")
                uiNotification = notification
            }
        }

        FavoriteScreen(
            favoriteViewState = viewState,
            notification = uiNotification,
            onRefresh = viewModel::getFavoriteMovies,
            navToDetail = { movieId -> navController.navigate(Screen.Detail.withMovieId(movieId)) },
            modifier = modifier
        )
    }

}

fun NavGraphBuilder.settingNode(
    navController: NavHostController,
    modifier: Modifier
) {
    composable(
        Screen.Settings.route,
    ) {
        val viewModel = koinViewModel<SettingViewModel>()
        val viewState by viewModel.viewState.collectAsStateWithLifecycle()
        var uiNotification by remember {
            mutableStateOf<UiNotification?>(null)
        }
        LaunchedEffect(Unit) {
            viewModel.notification.collect { notification ->
                Log.d("FavoriteViewModel", "Notification 1 received: $notification")
                uiNotification = notification
            }
        }
        SettingScreen(
            settingViewState = viewState,
            notification = uiNotification,
            modifier = modifier,
            onEvent = viewModel::onEvent
        )
    }

}

fun NavGraphBuilder.detailNode(
    navController: NavHostController,
) {
    composable(
        Screen.Detail.route,
        arguments = listOf(
            navArgument(DETAIL_MOVIE_ARGUMENT_KEY) { type = NavType.StringType }
        )
    ) { backStackEntry ->
        backStackEntry.arguments?.getString(DETAIL_MOVIE_ARGUMENT_KEY)?.let { movieId ->
            val viewModel = koinViewModel<DetailViewModel>()

            LaunchedEffect(Unit) {
                viewModel.getMovieDetail(movieId)
            }

            val viewState by viewModel.viewState.collectAsStateWithLifecycle()

            var uiNotification by remember {
                mutableStateOf<UiNotification?>(null)
            }

            LaunchedEffect(Unit) {
                viewModel.notification.collect { notification ->
                    uiNotification = notification
                }
            }

            DetailScreen(
                viewState = viewState,
                uiNotification,
                onBack = { navController.popBackStack() },
                onRefresh = { viewModel.getMovieDetail(movieId) },
                onUpdateFavorite = { newFavState ->
                    viewModel.updateFavoriteOfMovie(movieId, newFavState)
                },
            )
        }
    }
}