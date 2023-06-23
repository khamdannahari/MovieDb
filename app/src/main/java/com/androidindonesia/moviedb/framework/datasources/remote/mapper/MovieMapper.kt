package com.androidindonesia.moviedb.framework.datasources.remote.mapper

import com.androidindonesia.moviedb.business.model.*
import com.androidindonesia.moviedb.framework.datasources.remote.response.*
import com.androidindonesia.moviedb.framework.presentation.util.EmptyDefaultValue.orZero

val MoviesResponse.asModel: MoviesModel
    get() = MoviesModel(
        page = page.orZero(),
        totalPages = totalPages.orZero(),
        results = results?.map { it.asModel }.orEmpty(),
        totalResults = totalResults.orZero(),
    )

private val MovieItemResponse.asModel: MovieItemModel
    get() = MovieItemModel(
        title = title.orEmpty(),
        posterPath = posterPath.orEmpty(),
        id = id.orZero(),
    )

val MovieDetailResponse.asModel: MovieDetailModel
    get() = MovieDetailModel(
        title = title.orEmpty(),
        id = id.orZero(),
        overview = overview.orEmpty(),
    )

val GenresResponse.asModel: GenresModel
    get() = GenresModel(
        genres = genres?.map { it.asModel }.orEmpty()
    )

private val GenresItemResponse.asModel: GenresItemModel
    get() = GenresItemModel(
        name = name.orEmpty(),
        id = id.orZero()
    )

val VideosResponse.asModel: VideosModel
    get() = VideosModel(
        id = id.orZero(),
        results = results?.map { it.asModel }.orEmpty()
    )

private val VideoResultResponse.asModel: VideoResultModel
    get() = VideoResultModel(
        site = site.orEmpty(),
        id = id.orEmpty(),
        key = key.orEmpty()
    )


val ReviewsResponse.asModel: ReviewsModel
    get() = ReviewsModel(
        id = id.orZero(),
        results = results?.map { it.asModel }.orEmpty(),
        page = page.orZero(),
        totalPages = totalPages.orZero()
    )

private val ReviewResultResponse.asModel: ReviewResultModel
    get() = ReviewResultModel(
        id = id.orEmpty(),
        author = author.orEmpty(),
        content = content.orEmpty()
    )