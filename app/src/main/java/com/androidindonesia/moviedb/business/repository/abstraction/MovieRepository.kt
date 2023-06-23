package com.androidindonesia.moviedb.business.repository.abstraction

import com.androidindonesia.moviedb.business.model.GenresModel
import com.androidindonesia.moviedb.business.model.MovieDetailModel
import com.androidindonesia.moviedb.business.model.MoviesModel
import com.androidindonesia.moviedb.business.model.VideoModel
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getMovies(page: Int, genreIds: List<Int>): Flow<MoviesModel>
    suspend fun getMovieDetail(id: Int): Flow<MovieDetailModel>
    suspend fun getVideo(id: Int): Flow<VideoModel>
    suspend fun getGenres(): Flow<GenresModel>
}