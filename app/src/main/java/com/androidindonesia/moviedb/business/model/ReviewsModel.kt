package com.androidindonesia.moviedb.business.model

data class ReviewsModel(
    val id: Int = 0,
    val results: List<ReviewResultModel>,
    val page: Int = 0,
    val totalPages: Int = 0,
)