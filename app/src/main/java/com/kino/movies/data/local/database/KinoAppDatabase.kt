package com.kino.movies.data.local.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.kino.movies.data.local.dao.MovieDao
import com.kino.movies.data.local.entity.MovieEntity

@Database(
    entities = [MovieEntity::class], autoMigrations = [
        AutoMigration(from = 2, to = 3)
    ], version = 3, exportSchema = true
)
abstract class KinoAppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}
