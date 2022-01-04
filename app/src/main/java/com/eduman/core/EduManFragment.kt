package com.eduman.core

import androidx.fragment.app.Fragment
import com.eduman.core.Constants.Companion.MAX_LOGGING_TAG_LENGTH

open class EduManFragment(
    private val loggingTag: String
) : Fragment() {

    fun getLogTag(): String {
        "EduMan#$loggingTag".let { tag ->
            return if (tag.length > MAX_LOGGING_TAG_LENGTH) {
                tag.substring(0, MAX_LOGGING_TAG_LENGTH)
            } else {
                tag
            }
        }
    }

    protected fun setActionBarTitle(actionBarTitle: String?) {
        (activity as? EduManActivity)?.setActionBarTitle(actionBarTitle)
    }

    protected fun setActionBarSubTitle(actionBarSubTitle: String?) {
        (activity as? EduManActivity)?.setActionBarSubTitle(actionBarSubTitle)
    }

}