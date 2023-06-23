package com.androidindonesia.moviedb.framework.datasources.remote.response

import com.google.gson.annotations.SerializedName

data class SpokenLanguagesItemResponse(
    @SerializedName("name")
    val name: String = "",
    @SerializedName("iso_639_1")
    val iso: String = "",
    @SerializedName("english_name")
    val englishName: String = ""
)