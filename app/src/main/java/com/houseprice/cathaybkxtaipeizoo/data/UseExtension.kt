package com.houseprice.cathaybkxtaipeizoo.data

import android.view.View

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


fun View.gone() = run { visibility = View.GONE }

fun View.visible() = run { visibility = View.VISIBLE }

fun View.invisible() = run { visibility = View.INVISIBLE }