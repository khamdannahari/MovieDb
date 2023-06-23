package com.androidindonesia.moviedb.framework.datasources.remote.mapper

import com.androidindonesia.moviedb.business.model.*
import com.androidindonesia.moviedb.framework.datasources.remote.response.*

val MoviesResponse.asModel: MoviesModel
    get() = MoviesModel(
        page = page,
        totalPages = totalPages,
        results = results.map { it.asModel },
        totalResults = totalResults,
    )

private val MovieItemResponse.asModel: MovieItemModel
    get() = MovieItemModel(
        overview = overview,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        video = video,
        title = title,
        genreIds = genreIds,
        posterPath = posterPath,
        backdropPath = backdropPath.orEmpty(),
        releaseDate = releaseDate,
        popularity = popularity,
        voteAverage = voteAverage,
        id = id,
        adult = adult,
        voteCount = voteCount,
    )

val MovieDetailResponse.asModel: MovieDetailModel
    get() = MovieDetailModel(
        originalLanguage = originalLanguage,
        imdbId = imdbId,
        video = video,
        title = title,
        backdropPath = backdropPath,
        revenue = revenue,
        genres = emptyList(),
        popularity = popularity,
        productionCountries = emptyList(),
        id = id,
        voteCount = voteCount,
        budget = budget,
        overview = overview,
        originalTitle = originalTitle,
        runtime = runtime,
        posterPath = posterPath,
        spokenLanguages = emptyList(),
        productionCompanies = emptyList(),
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        tagline = tagline,
        adult = adult,
        homepage = homepage,
        status = status,
    )

val GenresResponse.asModel: GenresModel
    get() = GenresModel(
        genres = genres.map { it.asModel }
    )

private val GenresItemResponse.asModel: GenresItemModel
    get() = GenresItemModel(
        name = name,
        id = id
    )

val VideoResponse.asModel: VideoModel
    get() = VideoModel(
        id = id,
        results = results.map { it.asModel }
    )

private val VideoResultResponse.asModel: VideoResultModel
    get() = VideoResultModel(
        site = site,
        size = size,
        iso = iso,
        name = name,
        official = official,
        id = id,
        type = type,
        publishedAt = publishedAt,
        key = key
    )