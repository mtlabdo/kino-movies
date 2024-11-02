package com.kino.movies.data.local.database

import android.content.Context
import androidx.room.Room


const val DATABASE_NAME = "kino_movies.db"

class DatabaseBuilder(private val context: Context) {
    fun buildDatabase() = Room.databaseBuilder(context, KinoAppDatabase::class.java, DATABASE_NAME).build()
}