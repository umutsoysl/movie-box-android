package com.umut.soysal.mobile.moviebox

import com.umut.soysal.mobile.moviebox.data.remote.model.Genre
import com.umut.soysal.mobile.moviebox.data.remote.model.MovieListModel
import com.umut.soysal.mobile.moviebox.data.remote.model.MovieModel

object MovieListMockkModel {

    fun getMockMovieListResponse() = MovieListModel(
        page = 1,
        results = listOf(getMockMovieModel()),
        totalPages = 10,
        totalResults = 20
    )

    private fun getMockMovieModel() = MovieModel(
        id = 1,
        adult = false,
        genres = listOf(Genre(1,"")),
        backdropPath = "",
        posterPath = "",
        budget = 0,
        originalLanguage = "",
        originalTitle = "",
        title = "",
        status = "",
        tagLine = "",
        video = true,
        voteCount = 2,
        voteAverage = 9.0,
        overview = "",
        popularity = 0.0,
        releaseDate = ""
    )
}