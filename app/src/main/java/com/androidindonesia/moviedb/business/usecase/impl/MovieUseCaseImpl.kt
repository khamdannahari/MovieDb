package com.androidindonesia.moviedb.business.usecase.impl

import com.androidindonesia.moviedb.business.model.*
import com.androidindonesia.moviedb.business.repository.abstraction.MovieRepository
import com.androidindonesia.moviedb.business.usecase.abstraction.MovieUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieUseCaseImpl @Inject constructor(
    private val repository: MovieRepository
) : MovieUseCase {

    override suspend fun getGenres(): Flow<GenresModel> =
        repository.getGenres()

    override suspend fun getMovies(page: Int, genreIds: List<Int>): Flow<MoviesModel> =
        repository.getMovies(page, genreIds)

    override suspend fun getMovieDetail(id: Int): Flow<MovieDetailModel> =
        repository.getMovieDetail(id)

    override suspend fun getVideos(id: Int): Flow<VideosModel> =
        repository.getVideos(id)

    override suspend fun getReviews(id: Int): Flow<ReviewsModel> =
        repository.getReviews(id)

}