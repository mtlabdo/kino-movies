package com.kino.movies.presentation.navhost

import androidx.compose.runtime.Composable
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
            HomeScreen(navController, viewModel) { movieId ->
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