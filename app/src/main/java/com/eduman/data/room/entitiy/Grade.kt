package com.eduman.data.room.entitiy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 * This class represents a Grade in the local database
 *
 * @property id The unique ID of the grade - [Int]?
 * @property createdAt The timestamp at which the grade has been created - [Date]
 * @property grade The actual grade - [Float]
 * @property weighting The weighting of the grade - [Float]
 * @property subjectId The ID of the associated subject - [Int]
 * @property testId The ID of the associated test (optional) - [Int]?
 */
@Entity(tableName = "grades")
data class Grade(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,

    @ColumnInfo(name = "created_at")
    var createdAt: Date = Calendar.getInstance().time,

    @ColumnInfo(name = "grade")
    var grade: Float,

    @ColumnInfo(name = "weighting")
    var weighting: Float,

    @ColumnInfo(name = "subject_id")
    var subjectId: Int,

    @ColumnInfo(name = "test_id")
    var testId: Int? = null
)