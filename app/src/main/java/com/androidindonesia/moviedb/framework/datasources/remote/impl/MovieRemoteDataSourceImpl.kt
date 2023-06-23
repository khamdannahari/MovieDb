package com.androidindonesia.moviedb.framework.datasources.remote.impl

import com.androidindonesia.moviedb.business.model.*
import com.androidindonesia.moviedb.framework.datasources.remote.abstraction.MovieRemoteDataSource
import com.androidindonesia.moviedb.framework.datasources.remote.mapper.asModel
import com.androidindonesia.moviedb.framework.datasources.remote.service.MovieApi
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(
    private val api: MovieApi
) : MovieRemoteDataSource {

    override suspend fun getGenres(): GenresModel =
        api.getGenres().asModel

    override suspend fun getMovies(page: Int, genreIds: List<Int>): MoviesModel =
        api.getMovies(page, genreIds.joinToString(",")).asModel

    override suspend fun getMovieDetail(id: Int): MovieDetailModel =
        api.getMovieDetail(id).asModel

    override suspend fun getVideos(id: Int): VideosModel =
        api.getVideos(id).asModel

    override suspend fun getReviews(id: Int): ReviewsModel =
        api.getReviews(id).asModel

}