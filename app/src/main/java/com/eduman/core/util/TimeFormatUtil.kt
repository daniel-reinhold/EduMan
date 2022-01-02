package com.eduman.core.util

import com.eduman.data.util.Time

class TimeFormatUtil {

    companion object {

        fun formatTimeDefault(time: Time): String {
            return "${time.hour}:${time.minute}"
        }

    }

}