package com.umut.soysal.mobile.moviebox

import com.google.common.truth.Truth.assertThat
import com.umut.soysal.mobile.moviebox.core.ui.state.ResponseState
import com.umut.soysal.mobile.moviebox.data.remote.usecase.MovieUseCase
import com.umut.soysal.mobile.moviebox.feature.movielist.MovieViewModel
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieViewModelTest {
    private val mockUseCase = mockk<MovieUseCase>()

    private lateinit var movieViewModel: MovieViewModel

    @Before
    fun setupViewModel() {
        Dispatchers.setMain(TestCoroutineDispatcher())
        movieViewModel = MovieViewModel(mockUseCase)
    }

    @Test
    fun `ResponseState check Loading`() {
        val currentState = movieViewModel.getResponse()
        assertThat(currentState.value is ResponseState.Loading).isTrue()
    }

    @Test
    fun `ResponseState check Success`() {
        every { movieViewModel.getPopularMovie(1) }

        val currentState = movieViewModel.getResponse()
        assertThat(currentState.value is ResponseState.Success).isTrue()
    }

    @Test
    fun errorTest() {
        val list = ArrayList<Int>()
        list.add(1)
        list.add(3)

        assertThat(list.contains(1)).isFalse()
    }
}