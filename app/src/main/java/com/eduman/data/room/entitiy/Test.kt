package com.eduman.data.room.entitiy

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.*

/**
 * This class represents a test in the local database
 *
 * @property id The unique ID of the test - [Int]?
 * @property topic The topic of the test - [String]
 * @property date The date (and time) when the test is written - [Date]
 * @property createdAt The timestamp at which the test has been created - [Date]
 * @property subjectId The ID of the associated subject - [Int]
 */
@Parcelize
@Entity(tableName = "tests")
data class Test(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,

    @ColumnInfo(name = "topic")
    var topic: String,

    @ColumnInfo(name = "date")
    var date: Date,

    @ColumnInfo(name = "created_at")
    var createdAt: Date = Calendar.getInstance().time,

    @ColumnInfo(name = "subject_id")
    var subjectId: Int
) : Parcelable