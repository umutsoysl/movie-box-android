package com.umut.soysal.mobile.moviebox.data.remote.usecase

import com.umut.soysal.mobile.moviebox.core.ui.state.ResponseState
import com.umut.soysal.mobile.moviebox.data.remote.model.MovieListModel
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    suspend fun getPopularMovie(page: Int = 1): Flow<ResponseState<MovieListModel>>
}