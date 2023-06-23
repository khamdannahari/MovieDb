package com.androidindonesia.moviedb.business.model

data class VideoResultModel(
    val site: String = "",
    val size: Int = 0,
    val iso: String = "",
    val name: String = "",
    val official: Boolean = false,
    val id: String = "",
    val type: String = "",
    val publishedAt: String = "",
    val key: String = ""
) {

    val isYoutubeSite = site.lowercase() == YOUTUBE_SITE

    private companion object {
        const val YOUTUBE_SITE = "youtube"
    }
}