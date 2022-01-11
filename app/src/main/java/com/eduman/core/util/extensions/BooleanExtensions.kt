package com.eduman.core.util.extensions

fun Boolean?.orFalse(): Boolean {
    return this ?: false
}

fun Boolean?.orTrue(): Boolean {
    return this ?: false
}