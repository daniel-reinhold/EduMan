package com.eduman.core.util

import android.content.Context
import android.util.TypedValue

class GeneralUtil {

    companion object {

        fun dpToPx (context: Context?, dp: Int): Int {
            return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp.toFloat(),
                context?.resources?.displayMetrics
            ).toInt()
        }

    }

}