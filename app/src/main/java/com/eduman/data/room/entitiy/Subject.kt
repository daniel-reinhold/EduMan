package com.eduman.data.room.entitiy

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

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