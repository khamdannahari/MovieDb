package com.androidindonesia.moviedb.framework.presentation.util

sealed class PaginationDataState<out T> {
    data class First<out T>(val data: T) : PaginationDataState<T>()
    data class Append<out T>(val data: T) : PaginationDataState<T>()
    data class Failure(val throwable: Throwable) : PaginationDataState<Nothing>()
    object PaginationEnded : PaginationDataState<Nothing>()
    object FirstLoading : PaginationDataState<Nothing>()
    object PaginationLoading : PaginationDataState<Nothing>()
}
