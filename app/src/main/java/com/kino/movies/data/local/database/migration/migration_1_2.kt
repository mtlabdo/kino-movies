package com.kino.movies.data.local.database.migration

import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val migration_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("CREATE INDEX IF NOT EXISTS index_title ON movie (title)");

    }
}

// Auto migration spec
class MyAutoMigration : AutoMigrationSpec
