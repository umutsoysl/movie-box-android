package com.umut.soysal.mobile.moviebox.data.remote.repository

import com.umut.soysal.mobile.moviebox.core.ui.state.ResponseState
import com.umut.soysal.mobile.moviebox.data.remote.model.MovieListModel
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getPopularMovie(page: Int = 1): Flow<ResponseState<MovieListModel>>
}