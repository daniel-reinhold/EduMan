package com.eduman.data.room.core

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.eduman.data.room.converters.DateConverter
import com.eduman.data.room.dao.CoreDao
import com.eduman.data.room.dao.GradeDAO
import com.eduman.data.room.dao.SubjectDAO
import com.eduman.data.room.dao.TestDAO
import com.eduman.data.room.entitiy.Grade
import com.eduman.data.room.entitiy.Subject
import com.eduman.data.room.entitiy.Test

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

    abstract fun getCoreDAO(): CoreDao
    abstract fun getSubjectDAO(): SubjectDAO
    abstract fun getGradeDAO(): GradeDAO
    abstract fun getTestDAO(): TestDAO

}