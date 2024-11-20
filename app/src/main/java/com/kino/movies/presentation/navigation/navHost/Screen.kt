package com.kino.movies.presentation.navigation.navHost


const val DETAIL_MOVIE_ARGUMENT_KEY = "movieId"

private const val HOME_ROUTE = "home_screen"
private const val FAVORITE_ROUTE = "favorite_screen"
private const val SETTINGS_ROUTE = "settings_screen"
private const val DETAIL_ROUTE = "details_screen/{$DETAIL_MOVIE_ARGUMENT_KEY}"


sealed class Screen(val route: String) {
    object Home : Screen(HOME_ROUTE)
    object Detail : Screen(DETAIL_ROUTE) {
        fun withMovieId(movieId: String): String {
            return DETAIL_ROUTE.replace("{$DETAIL_MOVIE_ARGUMENT_KEY}", movieId)
        }
    }

    object Favorite : Screen(FAVORITE_ROUTE)
    object Settings : Screen(SETTINGS_ROUTE)

}
