package com.kino.movies.data.local.database.migration

import androidx.room.RenameTable
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

// Manual migration spec
val migration_1_2  = object : Migration(1,2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        TODO("Not yet implemented")
    }
}

// Auto migration spec
@RenameTable(fromTableName = "####", toTableName = "####")
class MyAutoMigration : AutoMigrationSpec
