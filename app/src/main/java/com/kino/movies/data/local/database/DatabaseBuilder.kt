package com.kino.movies.data.local.database

import android.content.Context
import androidx.room.Room
import com.kino.movies.data.local.database.migration.migration_1_2


const val DATABASE_NAME = "kino_movies.db"

class DatabaseBuilder(private val context: Context) {
    fun buildDatabase() =
        Room.databaseBuilder(context, KinoAppDatabase::class.java, DATABASE_NAME).addMigrations(
            migration_1_2
        ).fallbackToDestructiveMigrationOnDowngrade().build()
}