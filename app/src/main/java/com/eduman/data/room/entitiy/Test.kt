package com.eduman.data.room.entitiy

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "tests")
data class Test(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,

    @ColumnInfo(name = "topic")
    var topic: String,

    @ColumnInfo(name = "subject_id")
    var subjectId: Int,

    @ColumnInfo(name = "date")
    var date: Date,

    @ColumnInfo(name = "created_at")
    var createdAt: Date = Calendar.getInstance().time
) : Parcelable