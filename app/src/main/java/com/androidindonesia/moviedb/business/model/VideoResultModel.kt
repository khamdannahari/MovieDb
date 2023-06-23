package com.androidindonesia.moviedb.business.model

data class VideoResultModel(
    val site: String = "",
    val id: String = "",
    val key: String = ""
) {

    val isYoutubeSite = site.lowercase() == YOUTUBE_SITE

    private companion object {
        const val YOUTUBE_SITE = "youtube"
    }
}