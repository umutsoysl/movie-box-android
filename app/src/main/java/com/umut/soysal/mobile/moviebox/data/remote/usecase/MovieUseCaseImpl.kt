package com.umut.soysal.mobile.moviebox.data.remote.usecase

import com.umut.soysal.mobile.moviebox.core.di.IoDispatcher
import com.umut.soysal.mobile.moviebox.data.remote.model.MovieListModel
import com.umut.soysal.mobile.moviebox.data.remote.repository.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieUseCaseImpl @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val movieRepository: MovieRepository,
) : MovieUseCase {
    override suspend fun getPopularMovie(page: Int) = withContext(ioDispatcher) {
        movieRepository.getPopularMovie(page)
    }
}