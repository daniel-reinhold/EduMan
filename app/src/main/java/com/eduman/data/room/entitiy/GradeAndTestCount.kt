package com.eduman.data.room.entitiy

import androidx.room.ColumnInfo

class GradeAndTestCount(
    @ColumnInfo(name = "grade_count")
    var gradeCount: Int,

    @ColumnInfo(name = "test_count")
    var testCount: Int
)