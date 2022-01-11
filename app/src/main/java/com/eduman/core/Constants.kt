package com.eduman.core

import com.eduman.R
import com.eduman.data.util.Library

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

        val USED_LIBRARIES = listOf(
            Library("Android Core Library", "android.com", "https://developer.android.com/jetpack/androidx/releases/core", R.drawable.logo_android),
            Library("Kotlin", "kotlinlang.com", "https://kotlinlang.org/", R.drawable.logo_kotlin),
            Library("Android Room", "android.com", "https://developer.android.com/training/data-storage/room", R.drawable.logo_android),
            Library("Dagger-Hilt", "dagger.dev", "https://dagger.dev/hilt/", R.drawable.logo_injection),
            Library("Lottie", "lottiefiles.com", "https://lottiefiles.com/", R.drawable.logo_lottie),
            Library("QuadFlask Color Picker", "github.com", "https://github.com/QuadFlask/colorpicker", R.drawable.logo_color_picker),
            Library("Google Ads", "google.com", "https://ads.google.com", R.drawable.logo_google_ads)
        )
    }

}