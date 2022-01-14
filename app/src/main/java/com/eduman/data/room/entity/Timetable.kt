package com.eduman.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "timetables")
data class Timetable(
    @PrimaryKey(autoGenerate = true)
    var id: Int ? = null,

    @ColumnInfo(name = "name")
    var name: String
)