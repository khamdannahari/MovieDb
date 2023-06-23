package com.androidindonesia.moviedb.business.repository.impl

import com.androidindonesia.moviedb.business.model.*
import com.androidindonesia.moviedb.business.repository.abstraction.MovieRepository
import com.androidindonesia.moviedb.framework.datasources.remote.abstraction.MovieRemoteDataSource
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource
) : MovieRepository {

    override suspend fun getGenres(): Flow<GenresModel> = flow {
        emit(movieRemoteDataSource.getGenres())
    }.flowOn(IO)

    override suspend fun getMovies(page: Int, genreIds: List<Int>): Flow<MoviesModel> = flow {
        emit(movieRemoteDataSource.getMovies(page, genreIds))
    }.flowOn(IO)

    override suspend fun getMovieDetail(id: Int): Flow<MovieDetailModel> = flow {
        emit(movieRemoteDataSource.getMovieDetail(id))
    }.flowOn(IO)

    override suspend fun getVideos(id: Int): Flow<VideosModel> = flow {
        emit(movieRemoteDataSource.getVideos(id))
    }.flowOn(IO)

    override suspend fun getReviews(id: Int, page: Int): Flow<ReviewsModel> = flow {
        emit(movieRemoteDataSource.getReviews(id, page))
    }.flowOn(IO)

}