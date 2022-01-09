package com.eduman.data.room.entitiy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.eduman.data.util.Pin

@Entity(tableName = "settings")
data class Setting(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,

    @ColumnInfo(name = "user_name")
    var userName: String?,

    @ColumnInfo(name = "pin")
    var pin: Pin?,

    @ColumnInfo(name = "use_pin")
    var usePin: Boolean?
)