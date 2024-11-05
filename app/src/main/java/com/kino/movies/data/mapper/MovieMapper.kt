package com.kino.movies.data.mapper

import com.kino.movies.data.local.entity.MovieEntity
import com.kino.movies.data.network.dto.MovieDto
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