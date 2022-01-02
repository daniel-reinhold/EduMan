package com.eduman.core.util.formatter

import android.content.Context
import com.eduman.R
import java.text.SimpleDateFormat
import java.util.*

class DateTimeFormatter {

    companion object {

        fun formatDateTimeDefault(context: Context?, date: Date): String {
            return SimpleDateFormat(
                context?.getString(R.string.format_date_time_default),
                Locale.getDefault()
            ).format(date)
        }

    }

}