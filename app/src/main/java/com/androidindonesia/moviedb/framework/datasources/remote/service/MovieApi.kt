package com.androidindonesia.moviedb.framework.datasources.remote.service

import com.androidindonesia.moviedb.framework.datasources.remote.response.GenresResponse
import com.androidindonesia.moviedb.framework.datasources.remote.response.MovieDetailResponse
import com.androidindonesia.moviedb.framework.datasources.remote.response.MoviesResponse
import com.androidindonesia.moviedb.framework.datasources.remote.response.VideoResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET(MOVIES)
    suspend fun getMovies(
        @Query(PAGE) page: Int,
        @Query(WITH_GENRES) genres: String
    ): MoviesResponse

    @GET(MOVIE)
    suspend fun getMovieDetail(
        @Path(ID) id: Int
    ): MovieDetailResponse

    @GET(VIDEO)
    suspend fun getVideo(
        @Path(ID) id: Int
    ): VideoResponse

    @GET(GENRES)
    suspend fun getGenres(): GenresResponse

    private companion object {
        // PATH
        const val MOVIES = "discover/movie"
        const val ID = "id"
        const val MOVIE = "movie/{$ID}"
        const val GENRES = "genre/movie/list"
        const val VIDEO = "movie/{$ID}/videos"

        // QUERY
        const val PAGE = "page"
        const val WITH_GENRES = "with_genres"
    }
}