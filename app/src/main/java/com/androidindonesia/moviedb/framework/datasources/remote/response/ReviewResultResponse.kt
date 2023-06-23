package com.androidindonesia.moviedb.framework.datasources.remote.response

import com.google.gson.annotations.SerializedName

data class ReviewResultResponse(
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("author")
    val author: String? = "",
    @SerializedName("content")
    val content: String? = ""
)