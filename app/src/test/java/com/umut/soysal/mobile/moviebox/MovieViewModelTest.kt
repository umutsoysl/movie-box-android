package com.umut.soysal.mobile.moviebox

import com.google.common.truth.Truth.assertThat
import com.umut.soysal.mobile.moviebox.core.ui.state.ResponseState
import com.umut.soysal.mobile.moviebox.data.remote.usecase.MovieUseCase
import com.umut.soysal.mobile.moviebox.feature.movielist.MovieViewModel
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

    private val testScope = TestCoroutineScope(testDispatcher)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        movieViewModel = MovieViewModel(mockUseCase)
    }

    @Test
    fun `when service call then ResponseState should return Loading`() {
        testScope.runBlockingTest {
            val currentState = movieViewModel.getResponse()
            assertThat(currentState.value is ResponseState.Loading).isTrue()
        }
    }

    @Test
    fun `when service call then ResponseState should return Success`() {
        testScope.runBlockingTest {
            every { movieViewModel.getPopularMovie(1) }

            val currentState = movieViewModel.getResponse()
            assertThat(currentState.value is ResponseState.Success).isTrue()
        }
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