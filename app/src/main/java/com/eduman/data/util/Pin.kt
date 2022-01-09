package com.eduman.data.util

import java.nio.charset.StandardCharsets
import java.util.*

class Pin(private val encryptedPin: String?) {

    companion object {
        fun encryptPin(pin: String): String {
            return Base64.getEncoder().encodeToString(pin.encodeToByteArray())
        }
    }

    fun encrypt(): Pin {
        return Pin(encryptedPin)
    }

    fun decrypt(): String {
        return String(
            Base64.getDecoder().decode((encryptedPin ?: "").encodeToByteArray()),
            StandardCharsets.UTF_8
        )
    }

}