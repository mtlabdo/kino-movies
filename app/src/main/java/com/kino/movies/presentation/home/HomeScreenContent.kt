package com.kino.movies.presentation.home

import android.util.Log
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.kino.movies.R
import com.kino.movies.domain.model.Movie
import com.kino.movies.presentation.designsystem.component.KinoUiLoading

@Composable
fun HomeScreenContent(
    viewState: HomeViewState?,
    onRefresh: () -> Unit,
    toDetail: (movieId) -> Unit,
    modifier: Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {

        when  {
            viewState is HomeViewState.Loading -> {
                KinoUiLoading()
            }

            viewState is HomeViewState.MoviesReady  && viewState.movies.isNotEmpty() -> {
                MoviesList(movies = viewState.movies, onItemClick = toDetail)
            }

            else -> {
                // TODO MAKE COMPONENT (EMPTY CONTENT)
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(stringResource(R.string.empty_content))
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = onRefresh) {
                        Text(stringResource(R.string.retry))
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
                .data(movie.imageUrl)
                .size(MOVIE_THUMBNAIL_SIZE)
                .diskCacheKey(movie.imageUrl)
                .crossfade(false)
                .build(),
            // TODO : set placeholder & error
            placeholder = painterResource(R.drawable.placeholder_movie),
            error = painterResource(R.drawable.placeholder_movie),
            contentDescription = movie.title,
            contentScale = ContentScale.Crop,
            onError = {
                Log.e("MovieItem", "Error loading image", it.result.throwable)
            },
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


private const val MOVIE_THUMBNAIL_SIZE = 92
