package com.androidindonesia.moviedb.framework.datasources.remote.response

import com.google.gson.annotations.SerializedName

data class ReviewsResponse(
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("results")
    val results: List<ReviewResultResponse>? = emptyList(),
    @SerializedName("page")
    val page: Int? = 0,
    @SerializedName("total_pages")
    val totalPages: Int? = 0,
)