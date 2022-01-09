package com.eduman.data.room.converters

import androidx.room.TypeConverter
import com.eduman.data.util.Pin

class PinConverter {

    @TypeConverter
    fun pinToString(pin: Pin): String {
        return pin.decrypt()
    }

    @TypeConverter
    fun stringToPin(encryptedPin: String?): Pin {
        return Pin(encryptedPin)
    }

}