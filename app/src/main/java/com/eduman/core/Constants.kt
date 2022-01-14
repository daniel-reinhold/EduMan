package com.eduman.core

import com.eduman.R
import com.eduman.data.util.Library

class Constants {

    companion object {
        const val MAX_LOGGING_TAG_LENGTH = 23

        /**
         * The key for the Subject (used for transmitting data between destinations)
         */
        const val KEY_SUBJECT = "com.eduman.constants.KeySubject"

        /**
         * The key for the Test (used for transmitting data between destinations)
         */
        const val KEY_TEST = "com.eduman.constants.KeyTest"

        /**
         * The key for the Subject ID (used for transmitting data between destinations)
         */
        const val KEY_SUBJECT_ID = "com.eduman.constants.KeySubjectId"

        const val KEY_FORM_MODE = "com.eduman.constants.KeyFormMode"

        const val FORM_MODE_CREATE = 10
        const val FORM_MODE_EDIT = 20

        const val PREFERENCE_KEY = "com.eduman.SharedPreferences"
        const val KEY_SP_USERNAME = "com.eduman.constants.SharedPreferences.Username"
        const val KEY_SP_PIN = "com.eduman.constants.SharedPreferences.Pin"
        const val KEY_SP_USE_PIN = "com.eduman.constants.SharedPreferences.UsePin"
        const val KEY_SP_LESSON_DURATION = "com.eduman.constants.SharedPreferences.LessonDuration"

        const val DEFAULT_PIN_LENGTH: Int = 4

        val BANNER_AD_ID = if (App.IS_RELEASE)
            "ca-app-pub-6555431213345265/8349837158"
        else
            "ca-app-pub-3940256099942544/6300978111"

        val INTERSTITIAL_AD_UNIT_ID = if (App.IS_RELEASE)
            "ca-app-pub-6555431213345265/1746444940"
        else
            "ca-app-pub-3940256099942544/1033173712"

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