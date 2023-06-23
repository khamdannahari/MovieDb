package com.androidindonesia.moviedb.framework.presentation.util

import android.widget.ImageView
import coil.load
import coil.request.CachePolicy
import com.androidindonesia.moviedb.R

fun ImageView.loadMovieImage(imagePath: String) {
    val baseUrlMovieImage = "https://image.tmdb.org/t/p/original"
    this.load(baseUrlMovieImage.plus(imagePath)) {
        placeholder(R.drawable.ic_image)
        fallback(R.drawable.ic_image)
        error(R.drawable.ic_image)
        diskCachePolicy(CachePolicy.ENABLED)
        memoryCachePolicy(CachePolicy.ENABLED)
    }
}