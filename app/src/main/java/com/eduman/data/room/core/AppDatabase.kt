package com.eduman.data.room.core

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.eduman.data.room.converters.DateConverter
import com.eduman.data.room.converters.WeekDayConverter
import com.eduman.data.room.dao.*
import com.eduman.data.room.entity.*

/**
 * This class represents the local database
 */
@Database(
    entities = [
        Subject::class,
        Grade::class,
        Test::class,
        Timetable::class,
        TimetableDay::class,
        TimetableDayEntry::class
    ],
    version = 2
)
@TypeConverters(DateConverter::class, WeekDayConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getCoreDAO(): CoreDAO
    abstract fun getSubjectDAO(): SubjectDAO
    abstract fun getGradeDAO(): GradeDAO
    abstract fun getTestDAO(): TestDAO
    abstract fun getTimetableDAO(): TimetableDAO
    abstract fun getTimetableDayDAO(): TimetableDayDAO
    abstract fun getTimetableDayEntryDAO(): TimetableDayEntryDAO
}

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("CREATE TABLE `timetables` (`id` INTEGER, `name` TEXT NOT NULL, PRIMARY KEY(`id`))")
        database.execSQL("CREATE TABLE `timetable_days` (`id` INTEGER, `day` INTEGER NOT NULL, `timetable_id` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        database.execSQL("CREATE TABLE `timetable_day_entries` (`id` INTEGER, `name` TEXT NOT NULL, `room` TEXT NOT NULL, `position` INTEGER NOT NULL, `timetable_day_id` INTEGER NOT NULL, `subject_id` INTEGER NOT NULL, PRIMARY KEY(`id`))")
    }

}