package com.kino.movies.presentation.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kino.movies.domain.model.Movie
import com.kino.movies.presentation.designsystem.component.KinoUiLoading
import com.kino.movies.presentation.home.MovieItem
import com.kino.movies.presentation.home.movieId

@Composable
fun FavoriteScreenContent(
    viewState: FavoriteViewState?,
    onRefresh: () -> Unit,
    toDetail: (movieId) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {

        when (viewState) {
            is FavoriteViewState.Loading -> {
                KinoUiLoading()
            }

            is FavoriteViewState.FavoriteMoviesReady -> {
                if (viewState.favoriteMovies.isEmpty()) {
                    EmptyContent(
                        message = "Aucun film dans la liste des favoris"
                    )
                } else {
                    FavoriteMoviesList(
                        movies = viewState.favoriteMovies,
                        onItemClick = toDetail
                    )
                }
            }

            else -> {
                ErrorContent(
                    message = "Aucun résultat",
                    onRetry = onRefresh
                )
            }
        }
    }
}

@Composable
fun EmptyContent(message: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(message)
    }
}

@Composable
fun ErrorContent(
    message: String,
    onRetry: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(message)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onRetry) {
                Text("Réessayer")
            }
        }
    }
}

@Composable
fun FavoriteMoviesList(
    movies: List<Movie>,
    onItemClick: (movieId: String) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        itemsIndexed(items = movies, key = { _, item -> item.id }) { index, item ->
            val backgroundColor = if (index % 2 == 0) {
                MaterialTheme.colorScheme.background
            } else {
                MaterialTheme.colorScheme.secondaryContainer
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(backgroundColor)
            ) {
                MovieItem(item, onItemClick = onItemClick)
            }
        }
    }
}