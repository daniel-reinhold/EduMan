package com.eduman.data.room.core

import androidx.room.Database
import androidx.room.RoomDatabase
import com.eduman.data.room.dao.GradeDAO
import com.eduman.data.room.dao.SubjectDAO
import com.eduman.data.room.entitiy.Grade
import com.eduman.data.room.entitiy.Subject

@Database(
    entities = [
        Subject::class,
        Grade::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getSubjectDAO(): SubjectDAO
    abstract fun getGradeDAO(): GradeDAO

}