package com.eduman.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "timetable_day_entries")
data class TimetableDayEntry(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "room")
    var room: String,

    @ColumnInfo(name = "position")
    var position: Int,

    @ColumnInfo(name = "timetable_day_id")
    var timeTableDayId: Int,

    @ColumnInfo(name = "subject_id")
    var subjectId: Int
)