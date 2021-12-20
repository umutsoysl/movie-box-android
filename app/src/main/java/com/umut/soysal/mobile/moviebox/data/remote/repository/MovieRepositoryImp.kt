package com.umut.soysal.mobile.moviebox.data.remote.repository

import android.content.Context
import com.umut.soysal.mobile.moviebox.core.base.safeApiCall
import com.umut.soysal.mobile.moviebox.core.di.IoDispatcher
import com.umut.soysal.mobile.moviebox.core.ui.state.ResponseState
import com.umut.soysal.mobile.moviebox.data.remote.model.MovieListModel
import com.umut.soysal.mobile.moviebox.data.remote.service.MovieRemoteDataSource
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImp @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @ApplicationContext private val mContext: Context,
    private val movieDataSource: MovieRemoteDataSource
) : MovieRepository {

    override suspend fun getPopularMovie(page: Int): Flow<ResponseState<MovieListModel>> {
       return safeApiCall(context = mContext, dispatcher = ioDispatcher) {movieDataSource.getPopularMovie(page = page)}
    }
}