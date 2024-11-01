package com.kino.movies.data.local.entity

data class MovieEntity(
    val id: String,
    val title: String,
    val description: String,
    val rank: Int,
    val imageUrl: String,
    val genre: String,
    val thumbnailUrl: String,
    val rating: String,
    val year: String,
)