package com.androidindonesia.moviedb.business.model

data class MovieDetailModel(
    val originalLanguage: String = "",
    val imdbId: String = "",
    val video: Boolean = false,
    val title: String = "",
    val backdropPath: String = "",
    val revenue: Int = 0,
    val genres: List<GenresItemModel> = emptyList(),
    val popularity: Double = 0.0,
    val productionCountries: List<ProductionCountriesItemModel> = emptyList(),
    val id: Int = 0,
    val voteCount: Int = 0,
    val budget: Int = 0,
    val overview: String = "",
    val originalTitle: String = "",
    val runtime: Int = 0,
    val posterPath: String = "",
    val spokenLanguages: List<SpokenLanguagesItemModel> = emptyList(),
    val productionCompanies: List<ProductionCompaniesItemModel> = emptyList(),
    val releaseDate: String = "",
    val voteAverage: Double = 0.0,
    val tagline: String = "",
    val adult: Boolean = false,
    val homepage: String = "",
    val status: String = ""
)