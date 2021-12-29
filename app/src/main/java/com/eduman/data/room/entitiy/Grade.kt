package com.eduman.data.room.entitiy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "grades")
data class Grade(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,

    @ColumnInfo(name = "grade")
    var grade: Float,

    @ColumnInfo(name = "weighting")
    var weighting: Float,

    @ColumnInfo(name = "subject_id")
    var subjectId: Int
)