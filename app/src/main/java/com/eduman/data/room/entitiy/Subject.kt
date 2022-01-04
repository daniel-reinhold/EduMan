package com.eduman.data.room.entitiy

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

/**
 * This class represents a subject in the local database
 *
 * @property id The unique ID of the subject - [Int]?
 * @property title The title of the subject - [String]
 * @property color The color of the subject - [Int]
 * @property teacherName The name of the teacher who is teaching the subject - [String]
 */
@Parcelize
@Entity(tableName = "subjects")
data class Subject(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "color")
    var color: Int,

    @ColumnInfo(name = "teacher_name")
    var teacherName: String
) : Parcelable