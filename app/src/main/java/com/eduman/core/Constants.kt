package com.eduman.core

class Constants {

    companion object {
        const val MAX_LOGGING_TAG_LENGTH = 23

        /**
         * The key for the subject (used for transmitting data between destinations)
         */
        const val KEY_SUBJECT = "com.eduman.constants.KeySubject"

        const val PREFERENCE_KEY = "com.eduman.SharedPreferences"
        const val KEY_SP_USERNAME = "com.eduman.constants.SharedPreferences.Username"
        const val KEY_SP_PIN = "com.eduman.constants.SharedPreferences.Pin"
        const val KEY_SP_USE_PIN = "com.eduman.constants.SharedPreferences.UsePin"

        const val DEFAULT_PIN_LENGTH: Int = 4
    }

}