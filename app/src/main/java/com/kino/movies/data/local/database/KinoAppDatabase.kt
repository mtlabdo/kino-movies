package com.kino.movies.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kino.movies.data.local.dao.MovieDao
import com.kino.movies.data.local.entity.MovieEntity

@Database(entities = [MovieEntity::class], autoMigrations = [], version = 1, exportSchema = false)
abstract class KinoAppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}
