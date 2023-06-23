package com.androidindonesia.moviedb.framework.datasources.remote.abstraction

import com.androidindonesia.moviedb.business.model.*

interface MovieRemoteDataSource {
    suspend fun getGenres(): GenresModel
    suspend fun getMovies(page: Int, genreIds: List<Int>): MoviesModel
    suspend fun getMovieDetail(id: Int): MovieDetailModel
    suspend fun getVideos(id: Int): VideosModel
    suspend fun getReviews(id: Int, page: Int): ReviewsModel
}