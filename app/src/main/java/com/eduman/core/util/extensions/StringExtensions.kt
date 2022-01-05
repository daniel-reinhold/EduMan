package com.eduman.core.util.extensions

fun String?.toNonNullable(): String {
    return this ?: ""
}