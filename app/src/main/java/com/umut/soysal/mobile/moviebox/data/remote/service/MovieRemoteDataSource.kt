package com.umut.soysal.mobile.moviebox.data.remote.service

import com.umut.soysal.mobile.moviebox.data.remote.model.MovieListModel
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(
    private val movieService: MovieService
) {
    suspend fun getPopularMovie(page: Int): MovieListModel =
        movieService.getPopularMovie(page)
}