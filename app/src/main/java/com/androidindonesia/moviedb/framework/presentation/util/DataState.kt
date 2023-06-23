package com.androidindonesia.moviedb.framework.presentation.util

sealed class DataState<out T> {
    data class Success<out T>(val data: T) : DataState<T>()
    data class Failure(val throwable: Throwable) : DataState<Nothing>()
    object Loading : DataState<Nothing>()
}
