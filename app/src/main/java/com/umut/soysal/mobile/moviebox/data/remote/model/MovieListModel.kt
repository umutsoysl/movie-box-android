package com.umut.soysal.mobile.moviebox.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieListModel (
    val page: Long? = 0,
    val results: ArrayList<MovieModel>? = arrayListOf(),

    @Json(name = "total_pages")
    val totalPages: Long? = 0,

    @Json(name = "total_results")
    val totalResults: Long? = 0
)

@JsonClass(generateAdapter = true)
data class MovieModel(
    val adult: Boolean = false,
    var isMovie: Boolean = true,
    @Json(name = "backdrop_path")
    val backdropPath: String? = null,

    @Json(name = "genre_ids")
    val genreIDS: ArrayList<Long>? = arrayListOf(),
    val genres: List<Genre>? = arrayListOf(),
    val budget: Long? = 0,
    val id: Long? = 0,
    val homepage: String? = null,

    @Json(name = "imdb_id")
    val imdbID: String? = null,

    @Json(name = "original_language")
    val originalLanguage: String? = null,

    @Json(name = "original_title")
    val originalTitle: String? = null,

    val overview: String? = null,
    val popularity: Double? = 0.0,

    @Json(name = "poster_path")
    val posterPath: String? = null,

    @Json(name = "release_date")
    val releaseDate: String? = null,

    val status: String? = null,
    @Json(name = "tagline")
    val tagLine: String? = null,
    @Json(name = "title")
    val title: String? = null,
    val video: Boolean = false,

    @Json(name = "vote_average")
    val voteAverage: Double? = 0.0,

    @Json(name = "vote_count")
    val voteCount: Long? = 0
)

@JsonClass(generateAdapter = true)
data class Genre (
    val id: Long,
    val name: String,
    var isMovie: Boolean = true,
    var isSelect: Boolean = false
)