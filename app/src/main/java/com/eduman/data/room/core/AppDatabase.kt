package com.eduman.data.room.core

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.eduman.data.room.converters.DateConverter
import com.eduman.data.room.dao.CoreDAO
import com.eduman.data.room.dao.GradeDAO
import com.eduman.data.room.dao.SubjectDAO
import com.eduman.data.room.dao.TestDAO
import com.eduman.data.room.entitiy.Grade
import com.eduman.data.room.entitiy.Subject
import com.eduman.data.room.entitiy.Test

/**
 * This method represents the local database
 */
@Database(
    entities = [
        Subject::class,
        Grade::class,
        Test::class
    ],
    version = 2
)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getCoreDAO(): CoreDAO
    abstract fun getSubjectDAO(): SubjectDAO
    abstract fun getGradeDAO(): GradeDAO
    abstract fun getTestDAO(): TestDAO
}

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE `grades` ADD COLUMN test_id INTEGER")
    }
}