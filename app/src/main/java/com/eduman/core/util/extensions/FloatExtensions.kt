package com.eduman.core.util.extensions

/**
 * This function formats a float's decimal places
 * @param decimalPlaces The amount of decimal places after the comma which should be displayed
 * @param removeRedundant A boolean which indicates if the decimal places should be removed or not if the only consist of zeroes - [Boolean]
 */
fun Float.format(decimalPlaces: Int = 2, removeRedundant: Boolean = true): String {
    return if (this % 1.0 == 0.0 && removeRedundant) {
        String.format("%.0f", this)
    } else {
        String.format("%.${decimalPlaces}f", this)
    }
}

fun Float?.orZero(): Float {
    return this ?: 0.0F
}