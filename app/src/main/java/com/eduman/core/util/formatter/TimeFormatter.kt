package com.eduman.core.util.formatter

import com.eduman.data.util.Time

class TimeFormatter {

    companion object {

        fun formatTimeDefault(time: Time): String {
            return "${time.hour}:${time.minute}"
        }

    }

}