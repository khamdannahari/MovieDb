package com.androidindonesia.moviedb.framework.presentation.util

object EmptyDefaultValue {

    fun Int?.orZero() = this ?: 0
    fun Long?.orZero() = this ?: 0L
    fun Float?.orZero() = this ?: 0F
    fun Double?.orZero() = this ?: 0.0
    fun Boolean?.orFalse() = this ?: false

}