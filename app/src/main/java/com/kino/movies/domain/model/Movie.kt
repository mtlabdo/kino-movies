package com.kino.movies.domain.model

data class Movie(
    val id: String,
    val title: String? = null,
    val description: String? = null,
    val rank: Int? = null,
    val imageUrl: String? = null,
    val genre: String? = null,
    val thumbnailUrl: String? = null,
    val rating: String? = null,
    val year: String? = null,
    val releaseDate: String? = null,
    val note: String? = null,
    val isFavorite: Boolean = false,
)