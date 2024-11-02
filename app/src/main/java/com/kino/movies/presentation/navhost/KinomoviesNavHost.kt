package com.kino.movies.presentation.navhost

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.kino.movies.presentation.DETAIL_MOVIE_ARGUMENT_KEY
import com.kino.movies.presentation.Screen
import com.kino.movies.presentation.detail.DetailScreen
import com.kino.movies.presentation.detail.DetailViewModel
import com.kino.movies.presentation.home.HomeScreen
import com.kino.movies.presentation.home.HomeViewModel
import com.kino.movies.presentation.utils.UiNotification
import org.koin.androidx.compose.koinViewModel

@Composable
fun KinomoviesNavHost(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
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

            val viewStat by viewModel.viewState.collectAsStateWithLifecycle()
            HomeScreen(
                navController,
                viewStat,
                uiNotification,
                viewModel::searchMovies,
            ) { movieId ->
                navController.navigate(Screen.Detail.withMovieId(movieId))
            }
        }
        composable(
            Screen.Detail.route,
            arguments = listOf(
                navArgument(DETAIL_MOVIE_ARGUMENT_KEY) { type = NavType.StringType }
            )
        ) { backStackEntry ->
            backStackEntry.arguments?.getString(DETAIL_MOVIE_ARGUMENT_KEY)?.let { movieId ->
                DetailScreen(DetailViewModel(movieId)) {
                    navController.popBackStack()
                }
            }
        }
    }

}