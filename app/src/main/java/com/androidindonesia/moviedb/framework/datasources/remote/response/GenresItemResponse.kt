package com.androidindonesia.moviedb.framework.datasources.remote.response

import com.google.gson.annotations.SerializedName

data class GenresItemResponse(
    @SerializedName("name")
    val name: String = "",
    @SerializedName("id")
    val id: Int = 0
)