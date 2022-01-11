package com.eduman.core.util.extensions

import java.lang.NumberFormatException
import java.text.SimpleDateFormat
import java.util.*

fun Date.getHour(): Int {
    return try {
        SimpleDateFormat("HH", Locale.getDefault()).format(this).toInt()
    } catch (e: NumberFormatException) {
        0
    }
}