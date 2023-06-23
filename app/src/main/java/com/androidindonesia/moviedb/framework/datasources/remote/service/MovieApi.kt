package com.androidindonesia.moviedb.framework.datasources.remote.service

import com.androidindonesia.moviedb.framework.datasources.remote.response.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET(GENRES)
    suspend fun getGenres(): GenresResponse

    @GET(MOVIES)
    suspend fun getMovies(
        @Query(PAGE) page: Int,
        @Query(WITH_GENRES) genres: String
    ): MoviesResponse

    @GET(MOVIE)
    suspend fun getMovieDetail(
        @Path(ID) id: Int
    ): MovieDetailResponse

    @GET(VIDEOS)
    suspend fun getVideos(
        @Path(ID) id: Int
    ): VideosResponse

    @GET(REVIEWS)
    suspend fun getReviews(
        @Path(ID) id: Int
    ): ReviewsResponse

    private companion object {
        // PATH
        const val MOVIES = "discover/movie"
        const val ID = "id"
        const val MOVIE = "movie/{$ID}"
        const val GENRES = "genre/movie/list"
        const val VIDEOS = "movie/{$ID}/videos"
        const val REVIEWS = "movie/{$ID}/reviews"

        // QUERY
        const val PAGE = "page"
        const val WITH_GENRES = "with_genres"
    }
}