package com.androidindonesia.moviedb.framework.datasources.remote.response

import com.google.gson.annotations.SerializedName

data class VideoResponse(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("results")
    val results: List<VideoResultResponse> = emptyList()
)