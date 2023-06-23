package com.androidindonesia.moviedb.framework.datasources.remote.response

import com.google.gson.annotations.SerializedName

data class GenresResponse(
    @SerializedName("genres")
    val genres: List<GenresItemResponse>? = emptyList()
)