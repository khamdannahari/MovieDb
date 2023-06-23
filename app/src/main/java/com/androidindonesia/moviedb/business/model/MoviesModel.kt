package com.androidindonesia.moviedb.business.model

data class MoviesModel(
    val page: Int = 0,
    val totalPages: Int = 0,
    val results: List<MovieItemModel> = emptyList(),
    val totalResults: Int = 0
)