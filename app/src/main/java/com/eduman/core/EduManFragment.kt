package com.eduman.core

import androidx.fragment.app.Fragment
import com.eduman.core.Constants.Companion.MAX_LOGGING_TAG_LENGTH
import com.google.android.gms.ads.FullScreenContentCallback

open class EduManFragment(
    private val loggingTag: String
) : Fragment() {

    /**
     * This method returns a log tag for the fragment
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
    protected fun setActionBarTitle(actionBarTitle: String?) {
        (activity as? EduManActivity)?.setActionBarTitle(actionBarTitle)
    }

    /**
     * This method updates the title of the actionbar
     * @param actionBarTitleResId The resource ID of the action bar title - [String]
     */
    protected fun setActionBarTitle(actionBarTitleResId: Int) {
        (activity as? EduManActivity)?.setActionBarTitle(
            activity?.getString(actionBarTitleResId)
        )
    }

    /**
     * This method updates the subtitle of the actionbar
     * @param actionBarSubTitle The new subtitle of the actionbar - [String]
     */
    protected fun setActionBarSubTitle(actionBarSubTitle: String?) {
        (activity as? EduManActivity)?.setActionBarSubTitle(actionBarSubTitle)
    }

    /**
     * This method updates the subtitle of the actionbar
     * @param actionBarSubTitleResId The resource ID of the action bar tittle - [String]
     */
    protected fun setActionBarSubTitle(actionBarSubTitleResId: Int) {
        (activity as? EduManActivity)?.setActionBarSubTitle(
            activity?.getString(actionBarSubTitleResId)
        )
    }

    protected fun showInterstitialAd(callback: FullScreenContentCallback) {
        (activity as? EduManActivity)?.showInterstitialAd(callback)
    }

}