package com.androidindonesia.moviedb.business.model

data class VideosModel(
    val id: Int = 0,
    val results: List<VideoResultModel> = emptyList()
)