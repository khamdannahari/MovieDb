package com.androidindonesia.moviedb.framework.datasources.remote.response

import com.google.gson.annotations.SerializedName

data class VideoResultResponse(
    @SerializedName("site")
    val site: String = "",
    @SerializedName("size")
    val size: Int = 0,
    @SerializedName("iso_3166_1")
    val iso: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("official")
    val official: Boolean = false,
    @SerializedName("id")
    val id: String = "",
    @SerializedName("type")
    val type: String = "",
    @SerializedName("published_at")
    val publishedAt: String = "",
    @SerializedName("key")
    val key: String = ""
)