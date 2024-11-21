package com.kino.movies.presentation.detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.kino.movies.R
import com.kino.movies.domain.model.Movie
import com.kino.movies.presentation.designsystem.component.KinoUiLoading

@Composable
fun DetailScreenContent(
    viewState: DetailViewState?,
    onRefresh: () -> Unit = {},
    onUpdateFavorite: (newFavoriteState) -> Unit,
    modifier: Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        when (viewState) {
            is DetailViewState.Loading -> {
                KinoUiLoading()
            }

            is DetailViewState.MovieDetailReady -> {
                MovieDetail(
                    movieDetail = viewState.movieDetail,
                    onUpdateFavorite = onUpdateFavorite
                )
            }

            else -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
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
fun MovieDetail(movieDetail: Movie, onUpdateFavorite: (newFavoriteState) -> Unit = {}) {
    val screenHeight = LocalConfiguration.current.screenHeightDp
    var isFavorite by remember { mutableStateOf(movieDetail.isFavorite) }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
            .padding(12.dp),
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(movieDetail.imageUrl)
                .size(400)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.placeholder_movie),
            error = painterResource(R.drawable.placeholder_movie),
            contentDescription = movieDetail.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(MaterialTheme.shapes.medium)
                .align(Alignment.CenterHorizontally)
                .height((screenHeight * 0.60).dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = movieDetail.title ?: stringResource(R.string.no_title),
                maxLines = 2,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.weight(5f)
            )

            Button(
                onClick = {
                    isFavorite = !isFavorite
                    onUpdateFavorite(isFavorite)
                },
                modifier = Modifier
                    .size(48.dp)
                    .weight(1f),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 4.dp
                ),
                shape = CircleShape,
                contentPadding = PaddingValues(1.dp),

                ) {
                Icon(
                    imageVector = if (isFavorite) {
                        Icons.Filled.Favorite
                    } else {
                        Icons.Outlined.FavoriteBorder
                    },
                    tint = if (isFavorite) {
                        Color.Red
                    } else {
                        MaterialTheme.colorScheme.primaryContainer
                    },
                    contentDescription = "Favorite",
                    modifier = Modifier.size(24.dp)
                )
            }
        }


        Spacer(modifier = Modifier.height(8.dp))
        Text(stringResource(R.string.year, movieDetail.year.toString().ifEmpty { "-" }))

        Spacer(modifier = Modifier.height(8.dp))
        Text(stringResource(R.string.genre, movieDetail.genre ?: "-"))

        Spacer(modifier = Modifier.height(12.dp))

        Row {
            CardLabel(text = "${movieDetail.rating ?: "-"}/10", icon = Icons.Default.Star)
            Spacer(modifier = Modifier.width(8.dp))
            CardLabel(text = stringResource(R.string.rank, movieDetail.rank.toString().ifEmpty { "-" }))
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = movieDetail.description ?: "-",
            style = MaterialTheme.typography.bodyLarge
        )

    }

}

@Composable
fun CardLabel(text: String, icon: ImageVector? = null) {
    Card(
        modifier = Modifier.height(32.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        ),
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(1.dp, Color.LightGray)
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            icon?.let {
                Icon(
                    imageVector = it,
                    contentDescription = text,
                    modifier = Modifier.size(24.dp),
                    tint = Color.Yellow
                )
                Spacer(modifier = Modifier.width(6.dp))
            }

            Text(text = text, fontSize = 16.sp)
        }
    }
}

typealias newFavoriteState = Boolean