package com.androidindonesia.moviedb.framework.datasources.remote.response

import com.google.gson.annotations.SerializedName

data class VideoResultResponse(
    @SerializedName("site")
    val site: String? = "",
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("key")
    val key: String? = ""
)