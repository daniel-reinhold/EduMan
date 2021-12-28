package com.eduman.core

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.eduman.core.Constants.Companion.MAX_LOGGING_TAG_LENGTH

open class EduManFragment(
    @LayoutRes layoutResId: Int,
    tag: String
) : Fragment(layoutResId) {

    protected val loggingTag = "EduMan#$tag"
        .padEnd(MAX_LOGGING_TAG_LENGTH, ' ')
        .substring(0, MAX_LOGGING_TAG_LENGTH)

}