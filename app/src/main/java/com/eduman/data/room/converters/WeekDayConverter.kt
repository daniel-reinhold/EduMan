package com.eduman.data.room.converters

import androidx.room.TypeConverter
import com.eduman.data.util.WeekDay

class WeekDayConverter {

    @TypeConverter
    fun weekDayToInt(weekDay: WeekDay): Int {
        return when (weekDay) {
            WeekDay.MONDAY -> 0
            WeekDay.TUESDAY -> 1
            WeekDay.WEDNESDAY -> 2
            WeekDay.THURSDAY -> 3
            WeekDay.FRIDAY-> 4
            WeekDay.SATURDAY -> 5
            WeekDay.SUNDAY -> 6
        }
    }

    @TypeConverter
    fun intToWeekDay(int: Int): WeekDay {
        return when (int) {
            1 -> WeekDay.MONDAY
            2 -> WeekDay.TUESDAY
            3 -> WeekDay.WEDNESDAY
            4 -> WeekDay.THURSDAY
            5 -> WeekDay.FRIDAY
            6 -> WeekDay.SATURDAY
            7 -> WeekDay.SUNDAY
            else -> WeekDay.MONDAY
        }
    }

}