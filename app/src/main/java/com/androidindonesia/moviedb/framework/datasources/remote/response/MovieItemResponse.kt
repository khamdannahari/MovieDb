package com.androidindonesia.moviedb.framework.datasources.remote.response

import com.google.gson.annotations.SerializedName

data class MovieItemResponse(
    @SerializedName("original_title")
    val originalTitle: String? = "",
    @SerializedName("poster_path")
    val posterPath: String? = "",
    @SerializedName("id")
    val id: Int? = 0
)