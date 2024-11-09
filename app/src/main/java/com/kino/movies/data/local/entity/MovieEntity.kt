package com.kino.movies.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "movie",
    indices = [Index(value = ["title"])]
)
data class MovieEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val description: String,
    val rank: Int,
    val imageUrl: String? = null,
    val genre: String,
    val thumbnailUrl: String,
    val rating: String,
    val year: String,
    @ColumnInfo(defaultValue = "0")
    val favorite: Boolean,
)