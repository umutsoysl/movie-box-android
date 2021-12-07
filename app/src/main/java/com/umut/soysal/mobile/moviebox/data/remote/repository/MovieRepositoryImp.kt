package com.umut.soysal.mobile.moviebox.data.remote.repository

import com.umut.soysal.mobile.moviebox.core.di.IoDispatcher
import com.umut.soysal.mobile.moviebox.data.remote.model.MovieListModel
import com.umut.soysal.mobile.moviebox.data.remote.service.MovieRemoteDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRepositoryImp @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val movieDataSource: MovieRemoteDataSource
) : MovieRepository {

    override suspend fun getPopularMovie(page: Int): MovieListModel {
        return movieDataSource.getPopularMovie(page = page)
    }
}