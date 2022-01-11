package com.eduman.data.room.core

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.eduman.data.room.converters.DateConverter
import com.eduman.data.room.dao.*
import com.eduman.data.room.entity.Grade
import com.eduman.data.room.entity.Subject
import com.eduman.data.room.entity.Test

/**
 * This method represents the local database
 */
@Database(
    entities = [
        Subject::class,
        Grade::class,
        Test::class
    ],
    version = 1
)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getCoreDAO(): CoreDAO
    abstract fun getSubjectDAO(): SubjectDAO
    abstract fun getGradeDAO(): GradeDAO
    abstract fun getTestDAO(): TestDAO
}