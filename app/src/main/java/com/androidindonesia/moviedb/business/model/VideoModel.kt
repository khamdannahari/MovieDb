package com.androidindonesia.moviedb.business.model

data class VideoModel(
    val id: Int = 0,
    val results: List<VideoResultModel> = emptyList()
)