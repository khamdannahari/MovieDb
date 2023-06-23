package com.androidindonesia.moviedb.framework.datasources.remote.abstraction

import com.androidindonesia.moviedb.business.model.GenresModel
import com.androidindonesia.moviedb.business.model.MovieDetailModel
import com.androidindonesia.moviedb.business.model.MoviesModel
import com.androidindonesia.moviedb.business.model.VideoModel

interface MovieRemoteDataSource {
    suspend fun getMovies(page: Int, genreIds: List<Int>): MoviesModel
    suspend fun getMovieDetail(id: Int): MovieDetailModel
    suspend fun getVideo(id: Int): VideoModel
    suspend fun getGenres(): GenresModel
}