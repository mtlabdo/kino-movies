package com.kino.movies.data.mapper

import com.kino.movies.data.local.entity.MovieEntity
import com.kino.movies.data.network.IMAGE_BASE_URL
import com.kino.movies.data.network.dto.MovieDto
import com.kino.movies.data.network.dto._MovieDto
import com.kino.movies.domain.model.Movie

fun MovieDto.toMovie() = Movie(
    id = id,
    title = title,
    rank = rank,
    description = description,
    imageUrl = image,
    genre = genre.joinToString(", "),
    thumbnailUrl = thumbnail,
    rating = rating,
    year = year.toString(),
)

fun MovieDto.toMovieEntity() = MovieEntity(
    id = id,
    title = title,
    rank = rank,
    description = description,
    imageUrl = image,
    genre = genre.joinToString(", "),
    thumbnailUrl = thumbnail,
    rating = rating,
    year = year.toString(),
    favorite = false
)

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

fun _MovieDto.toMovieEntity() = MovieEntity(
    id = id.toString(),
    title = title,
    rank = 0,
    description = overview,
    imageUrl = if (posterPath.isNullOrEmpty()) null else "$IMAGE_BASE_URL$posterPath",
    genre = genreIds.joinToString(", "),
    thumbnailUrl = posterPath ?: "",
    rating = voteAverage.toString(),
    year = releaseDate.take(4),
    favorite = false
)