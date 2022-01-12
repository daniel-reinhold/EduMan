package com.eduman.core.util.extensions

import android.content.res.Resources
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.eduman.R
import com.google.android.material.textview.MaterialTextView

fun MaterialTextView.setTextColorByResId(@ColorRes colorResId: Int) {
    this.setTextColor(
        try {
            ContextCompat.getColor(this.context, colorResId)
        } catch (e: Resources.NotFoundException) {
            ContextCompat.getColor(this.context, R.color.text)
        }
    )
}