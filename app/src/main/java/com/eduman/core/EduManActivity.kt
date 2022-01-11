package com.eduman.core

import androidx.appcompat.app.AppCompatActivity
import com.eduman.core.Constants.Companion.MAX_LOGGING_TAG_LENGTH
import com.google.android.gms.ads.FullScreenContentCallback

/**
 * This class represents the Base activity for all in this project used Activities
 * @property loggingTag The logging tag for this activity
 */
abstract class EduManActivity(
    private val loggingTag: String
) : AppCompatActivity() {

    /**
     * This method returns a log tag for the activity
     * @return The log tag - [String]
     */
    fun getLogTag(): String {
        "EduMan#$loggingTag".let { tag ->
            return if (tag.length > MAX_LOGGING_TAG_LENGTH) {
                tag.substring(0, MAX_LOGGING_TAG_LENGTH)
            } else {
                tag
            }
        }
    }

    /**
     * This method updates the title of the actionbar
     * @param actionBarTitle The new title of the actionbar - [String]
     */
    fun setActionBarTitle(actionBarTitle: String?) {
        supportActionBar?.title = actionBarTitle
    }

    /**
     * This method updates the subtitle of the actionbar
     * @param actionBarSubTitle The new subtitle of the actionbar - [String]
     */
    fun setActionBarSubTitle(actionBarSubTitle: String?) {
        supportActionBar?.subtitle = actionBarSubTitle
    }

    open fun showInterstitialAd(callback: FullScreenContentCallback) = Unit

}