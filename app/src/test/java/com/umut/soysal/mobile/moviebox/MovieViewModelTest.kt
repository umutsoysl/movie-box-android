package com.umut.soysal.mobile.moviebox

import com.google.common.truth.Truth.assertThat
import com.umut.soysal.mobile.moviebox.core.ui.state.ResponseState
import com.umut.soysal.mobile.moviebox.data.remote.usecase.MovieUseCase
import com.umut.soysal.mobile.moviebox.feature.movielist.MovieViewModel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieViewModelTest {
    private val mockUseCase = mockk<MovieUseCase>()

    private lateinit var movieViewModel: MovieViewModel

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        movieViewModel = MovieViewModel(mockUseCase)
    }

    @Test
    fun `Get Popular Movie List From UseCase return Success`() = runBlockingTest {
        coEvery {
            mockUseCase.getPopularMovie(1)
        } returns (MovieListMockkModel.getMockResponseStateMovieListResponse())

        movieViewModel.getPopularMovie(1)
        assertThat(movieViewModel.movieList.value.isNullOrEmpty()).isFalse()
    }

    @Test
    fun errorTest() {
        val list = ArrayList<Int>()
        list.add(1)
        list.add(3)

        assertThat(list.contains(2)).isFalse()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
}