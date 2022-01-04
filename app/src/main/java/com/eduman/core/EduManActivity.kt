package com.eduman.core

import androidx.appcompat.app.AppCompatActivity
import com.eduman.core.Constants.Companion.MAX_LOGGING_TAG_LENGTH

open class EduManActivity(
    private val loggingTag: String
) : AppCompatActivity() {

    fun getLogTag(): String {
        "EduMan#$loggingTag".let { tag ->
            return if (tag.length > MAX_LOGGING_TAG_LENGTH) {
                tag.substring(0, MAX_LOGGING_TAG_LENGTH)
            } else {
                tag
            }
        }
    }

    fun setActionBarTitle(actionBarTitle: String?) {
        supportActionBar?.title = actionBarTitle
    }

    fun setActionBarSubTitle(actionBarSubTitle: String?) {
        supportActionBar?.subtitle = actionBarSubTitle
    }

}