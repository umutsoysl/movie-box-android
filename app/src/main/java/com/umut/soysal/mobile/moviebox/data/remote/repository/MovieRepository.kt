package com.umut.soysal.mobile.moviebox.data.remote.repository

import com.umut.soysal.mobile.moviebox.data.remote.model.MovieListModel

interface MovieRepository {
    suspend fun getPopularMovie(page: Int? = 1): MovieListModel
}