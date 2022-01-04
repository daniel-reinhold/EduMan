package com.eduman.data.room.converters

import androidx.room.TypeConverter
import java.util.*

/**
 * This converter converts [Date] to [Long] and [Long] to [Date]
 */
class DateConverter {

    /**
     * This method converts a date to a long
     * @param date The date to convert - [Date]
     * @return The to long converted date - [Long]
     */
    @TypeConverter
    fun dateToLong(date: Date): Long {
        return date.time
    }

    /**
     * This method converts a long to a date
     * @param long The long to convert - [Long]
     * @return The to date converted long - [Date]
     */
    @TypeConverter
    fun longToDate(long: Long): Date {
        return Date(long)
    }

}