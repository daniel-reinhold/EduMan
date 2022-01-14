package com.eduman.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.eduman.data.util.WeekDay

@Entity(tableName = "timetable_days")
data class TimetableDay(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,

    @ColumnInfo(name = "day")
    var day: WeekDay,

    @ColumnInfo(name = "timetable_id")
    var timetableId: Int
)
