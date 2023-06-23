package com.androidindonesia.moviedb.di

import com.androidindonesia.moviedb.business.repository.abstraction.MovieRepository
import com.androidindonesia.moviedb.business.repository.impl.MovieRepositoryImpl
import com.androidindonesia.moviedb.business.usecase.abstraction.MovieUseCase
import com.androidindonesia.moviedb.business.usecase.impl.MovieUseCaseImpl
import com.androidindonesia.moviedb.framework.datasources.remote.abstraction.MovieRemoteDataSource
import com.androidindonesia.moviedb.framework.datasources.remote.impl.MovieRemoteDataSourceImpl
import com.androidindonesia.moviedb.framework.datasources.remote.service.MovieApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MovieModule {

    @Provides
    @Singleton
    fun provideMovieRemoteDataSource(movieApi: MovieApi): MovieRemoteDataSource =
        MovieRemoteDataSourceImpl(movieApi)

    @Provides
    @Singleton
    fun provideMovieRepository(movieRemoteDataSource: MovieRemoteDataSource): MovieRepository =
        MovieRepositoryImpl(movieRemoteDataSource)

    @Provides
    @Singleton
    fun provideMovieUseCase(movieRepository: MovieRepository): MovieUseCase =
        MovieUseCaseImpl(movieRepository)

}