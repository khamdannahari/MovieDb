package com.androidindonesia.moviedb.business.repository.abstraction

import com.androidindonesia.moviedb.business.model.*
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getGenres(): Flow<GenresModel>
    suspend fun getMovies(page: Int, genreIds: List<Int>): Flow<MoviesModel>
    suspend fun getMovieDetail(id: Int): Flow<MovieDetailModel>
    suspend fun getVideos(id: Int): Flow<VideosModel>
    suspend fun getReviews(id: Int): Flow<ReviewsModel>
}