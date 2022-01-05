package com.eduman.core.util

import java.util.*

class DateTimeUtil {

    companion object {

        fun dateAndTimeToDateTime(date: Calendar, time: Calendar): Date {
            return Calendar.getInstance().apply {
                set(Calendar.YEAR, date.get(Calendar.YEAR))
                set(Calendar.MONTH, date.get(Calendar.MONTH))
                set(Calendar.DAY_OF_MONTH, date.get(Calendar.DAY_OF_MONTH))
                set(Calendar.HOUR_OF_DAY, time.get(Calendar.HOUR_OF_DAY))
                set(Calendar.MINUTE, time.get(Calendar.MINUTE))
            }.time
        }

    }

}