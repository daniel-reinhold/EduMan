package com.eduman.core.util

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.TypedValue

class GeneralUtil {

    companion object {

        fun dpToPx(context: Context?, dp: Int): Int {
            return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp.toFloat(),
                context?.resources?.displayMetrics
            ).toInt()
        }

        fun getColorStateList(colorInt: Int): ColorStateList {
            val color = Color.valueOf(colorInt).toArgb()
            return ColorStateList.valueOf(color)
        }

    }

}