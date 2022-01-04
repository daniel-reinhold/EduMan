package com.eduman.data.room.entitiy.relation

import androidx.room.ColumnInfo

/**
 * This class represents the Model for the grade- and test count for a specific subject
 *
 * @property gradeCount The amount of grades for the subject - [Int]
 * @property testCount The amount of tests for the subject - [Int]
 */
data class GradeAndTestCount(
    @ColumnInfo(name = "grade_count")
    var gradeCount: Int,

    @ColumnInfo(name = "test_count")
    var testCount: Int
)