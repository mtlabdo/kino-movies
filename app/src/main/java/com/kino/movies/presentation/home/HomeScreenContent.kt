package com.kino.movies.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.kino.movies.R
import com.kino.movies.domain.model.Movie
import com.kino.movies.presentation.designsystem.components.KinoUiLoading

@Composable
fun HomeScreenContent(
    viewState: HomeViewState?,
    onRefresh: () -> Unit,
    toDetail: (movieId) -> Unit,
    modifier: Modifier
) {

    Column(
        modifier = modifier.fillMaxSize(),
    ) {

        when (viewState) {
            is HomeViewState.Loading -> {
                KinoUiLoading()
            }

            is HomeViewState.MoviesReady -> {
                MoviesList(movies = viewState.movies, onItemClick = toDetail)
            }

            else -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Aucun résultat")
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = onRefresh) {
                        Text("Réessayer")
                    }
                }
            }
        }
    }
}

@Composable
fun MoviesList(movies: List<Movie>, onItemClick: (movieId) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        itemsIndexed(items = movies, key = { _, item ->
            item.id
        }) { index, item ->
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

@Composable
fun MovieItem(movie: Movie, onItemClick: (String) -> Unit) {
    Row(
        modifier = Modifier
            .clickable {
                onItemClick(movie.id)
            }
            .fillMaxWidth()
            .padding(all = 16.dp)

    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(movie.thumbnailUrl)
                .crossfade(true)
                .build(),
            // TODO : set placeholder & error
            placeholder = painterResource(R.drawable.ic_launcher_background),
            error = painterResource(R.drawable.ic_launcher_background),
            contentDescription = movie.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(MaterialTheme.shapes.medium)
                .size(92.dp),
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = movie.title ?: "-", maxLines = 1)
            Text(text = movie.description ?: "-", maxLines = 2)
        }
    }
}


