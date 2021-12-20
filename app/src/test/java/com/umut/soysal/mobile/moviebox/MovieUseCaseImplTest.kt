package com.umut.soysal.mobile.moviebox

import com.google.common.truth.Truth
import com.umut.soysal.mobile.moviebox.data.remote.repository.MovieRepository
import com.umut.soysal.mobile.moviebox.data.remote.usecase.MovieUseCaseImpl
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieUseCaseImplTest {

    @RelaxedMockK
    private lateinit var useCaseImpl: MovieUseCaseImpl

    private val repository = mockk<MovieRepository>()

    private val testDispatcher = TestCoroutineDispatcher()

    private val testScope = TestCoroutineScope(testDispatcher)


    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        useCaseImpl = MovieUseCaseImpl(testDispatcher, repository)
    }

    @Test
    @ExperimentalCoroutinesApi
    fun `when useCase getPopularMovie() service call then should return Success response`() = testScope.runBlockingTest {
        //given
        val mockMovieResponse = MovieListMockkModel.getMockResponseStateMovieListResponse()

        coEvery {
            repository.getPopularMovie(1)
        } returns mockMovieResponse

        //when
        val serviceCallResponse = useCaseImpl.getPopularMovie(1)

        //then
        Truth.assertThat(serviceCallResponse).isEqualTo(mockMovieResponse)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
}