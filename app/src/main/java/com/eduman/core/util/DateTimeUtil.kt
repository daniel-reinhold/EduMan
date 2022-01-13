package com.eduman.core.util

import java.util.*

class DateTimeUtil {

    companion object {

        fun dateAndTimeToDateTime(date: Calendar?, time: Calendar?): Date {
            return Calendar.getInstance().apply {
                set(Calendar.YEAR, date?.get(Calendar.YEAR) ?: 1970)
                set(Calendar.MONTH, date?.get(Calendar.MONTH) ?: 0)
                set(Calendar.DAY_OF_MONTH, date?.get(Calendar.DAY_OF_MONTH) ?: 1)
                set(Calendar.HOUR_OF_DAY, time?.get(Calendar.HOUR_OF_DAY) ?: 0)
                set(Calendar.MINUTE, time?.get(Calendar.MINUTE) ?: 0)
            }.time
        }

    }

}