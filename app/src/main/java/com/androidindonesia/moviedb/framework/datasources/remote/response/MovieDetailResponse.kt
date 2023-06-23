package com.androidindonesia.moviedb.framework.datasources.remote.response

import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("overview")
    val overview: String? = "",
)