package com.kino.movies.data.mapper

import com.kino.movies.data.local.entity.MovieEntity
import com.kino.movies.data.network.IMAGE_BASE_URL
import com.kino.movies.data.network.dto.MovieDto
import com.kino.movies.domain.model.Movie
import com.kino.movies.presentation.utils.format


fun MovieEntity.toMovie() = Movie(
    id = id,
    title = title,
    rank = rank,
    description = description,
    imageUrl = imageUrl,
    genre = genre,
    thumbnailUrl = thumbnailUrl,
    rating = rating,
    year = year,
    isFavorite = favorite
)

fun MovieDto.toMovieEntity() = MovieEntity(
    id = id.toString(),
    title = title,
    rank = popularity.toInt(),
    description = overview,
    imageUrl = if (posterPath.isNullOrEmpty()) null else "$IMAGE_BASE_URL$posterPath",
    genre = genreIds.joinToString(", "),
    thumbnailUrl = posterPath ?: "",
    rating = voteAverage.format(1),
    year = releaseDate.take(4),
    favorite = false
)