package com.eduman.core

import androidx.appcompat.app.AppCompatActivity
import com.eduman.core.Constants.Companion.MAX_LOGGING_TAG_LENGTH

open class EduManActivity(tag: String) : AppCompatActivity() {

    protected val loggingTag = "EduMan#$tag"
        .padEnd(MAX_LOGGING_TAG_LENGTH, ' ')
        .substring(0, MAX_LOGGING_TAG_LENGTH)

}