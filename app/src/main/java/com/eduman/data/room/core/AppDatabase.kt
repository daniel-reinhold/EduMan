package com.eduman.data.room.core

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.eduman.data.room.converters.DateConverter
import com.eduman.data.room.converters.PinConverter
import com.eduman.data.room.dao.*
import com.eduman.data.room.entitiy.Grade
import com.eduman.data.room.entitiy.Setting
import com.eduman.data.room.entitiy.Subject
import com.eduman.data.room.entitiy.Test

/**
 * This method represents the local database
 */
@Database(
    entities = [
        Subject::class,
        Grade::class,
        Test::class,
        Setting::class
    ],
    version = 3
)
@TypeConverters(DateConverter::class, PinConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getCoreDAO(): CoreDAO
    abstract fun getSubjectDAO(): SubjectDAO
    abstract fun getGradeDAO(): GradeDAO
    abstract fun getTestDAO(): TestDAO
    abstract fun getSettingDAO(): SettingDAO
}

val DATABASE_INIT_CALLBACK = object : RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        db.execSQL("INSERT INTO `settings` (id) VALUES (1)")
    }
}

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE `grades` ADD COLUMN test_id INTEGER")
    }
}

val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE `settings` ADD COLUMN use_pin INTEGER")
    }
}