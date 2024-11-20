package com.kino.movies.presentation.navigation.navHost

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.kino.movies.presentation.navigation.node.detailNode
import com.kino.movies.presentation.navigation.node.favoriteNode
import com.kino.movies.presentation.navigation.node.homeNode
import com.kino.movies.presentation.navigation.node.settingNode

@Composable
fun KinomoviesNavHost(
    navController: NavHostController, modifier: Modifier
) {
    NavHost(
        navController = navController, startDestination = Screen.Home.route
    ) {
        homeNode(navController, modifier)
        favoriteNode(navController, modifier)
        settingNode(navController, modifier)
        detailNode(navController, modifier)
    }
}






