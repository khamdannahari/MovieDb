package com.androidindonesia.moviedb.business.usecase.impl

import com.androidindonesia.moviedb.business.model.GenresModel
import com.androidindonesia.moviedb.business.model.MovieDetailModel
import com.androidindonesia.moviedb.business.model.MoviesModel
import com.androidindonesia.moviedb.business.model.VideoModel
import com.androidindonesia.moviedb.business.repository.abstraction.MovieRepository
import com.androidindonesia.moviedb.business.usecase.abstraction.MovieUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieUseCaseImpl @Inject constructor(
    private val repository: MovieRepository
) : MovieUseCase {

    override suspend fun getMovies(page: Int, genreIds: List<Int>): Flow<MoviesModel> =
        repository.getMovies(page, genreIds)

    override suspend fun getMovieDetail(id: Int): Flow<MovieDetailModel> =
        repository.getMovieDetail(id)

    override suspend fun getVideo(id: Int): Flow<VideoModel> =
        repository.getVideo(id)

    override suspend fun getGenres(): Flow<GenresModel> =
        repository.getGenres()

}