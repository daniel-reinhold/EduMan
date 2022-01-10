package com.eduman.core.util.extensions

fun Int?.orZero(): Int {
    return this ?: 0
}