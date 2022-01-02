package com.eduman.core.util

import android.content.Context
import com.eduman.R
import java.text.SimpleDateFormat
import java.util.*

class DateFormatUtil {

    companion object {

        fun formatDateDefault(context: Context?, date: Date): String {
            return SimpleDateFormat(
                context?.getString(R.string.format_date_default),
                Locale.getDefault()
            ).format(date)
        }

    }

}