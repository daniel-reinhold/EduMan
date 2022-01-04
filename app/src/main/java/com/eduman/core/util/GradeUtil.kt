package com.eduman.core.util

import androidx.annotation.ColorInt
import com.eduman.R
import com.eduman.data.room.entitiy.Grade

class GradeUtil {

    companion object {

        fun calculateGradeAverage(grades: List<Grade>): Float {
            var gradeSum = 0.0F
            var weightSum = 0.0F
            grades.forEach { grade ->
                gradeSum += grade.grade * grade.weighting
                weightSum += grade.weighting
            }

            return gradeSum / weightSum
        }

        fun gradeToColor(grade: Float): Int {
            return when(grade) {
                in 1.0F..2.5F -> R.color.success
                in 2.5F..4.5F -> R.color.warning
                in 4.5F..6.0F -> R.color.error
                else -> R.color.black
            }
        }

        fun formatGrade(grade: Float, decimalPlaces: Int): String {
            return String.format("%.${decimalPlaces}f", grade)
        }

    }

}