package com.umut.soysal.mobile.moviebox.data.remote.usecase

import com.umut.soysal.mobile.moviebox.data.remote.model.MovieListModel
import com.umut.soysal.mobile.moviebox.data.remote.repository.MovieRepository
import javax.inject.Inject

class MovieUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository,
) : MovieUseCase {
    override suspend fun getPopularMovie(page: Int): MovieListModel {
        return movieRepository.getPopularMovie(page)
    }
}