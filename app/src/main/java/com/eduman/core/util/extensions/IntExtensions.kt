package com.eduman.core.util.extensions

fun Int?.toNonNullable(): Int {
    return this ?: 0
}