package com.houseprice.cathaybkxtaipeizoo.data

fun <T> MutableList<T>?.orEmpty(): MutableList<T> {
    return this ?: mutableListOf()
}

fun String?.orEmpty(): String {
    return this ?: ""
}

fun Int?.orZero(): Int {
    return this ?: 0
}

fun Int?.orEmpty(): String {
    return this?.toString() ?: ""
}
