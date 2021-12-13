package com.umut.soysal.mobile.moviebox

import com.google.common.truth.Truth
import com.umut.soysal.mobile.moviebox.data.remote.model.MovieListModel
import com.umut.soysal.mobile.moviebox.data.remote.repository.MovieRepositoryImp
import com.umut.soysal.mobile.moviebox.data.remote.service.MovieRemoteDataSource
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test


@ExperimentalCoroutinesApi
class MovieRepositoryImplTest {

    private val testDispatcher = TestCoroutineDispatcher()

    private val testScope = TestCoroutineScope(testDispatcher)

    @RelaxedMockK
    private lateinit var repositoryImpl: MovieRepositoryImp

    private val mockkDataSource = mockk<MovieRemoteDataSource>()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        repositoryImpl = MovieRepositoryImp(testDispatcher, mockkDataSource)
    }

    @Test
    fun `when getPopularMovie() service call then should return Success response`() {
        testScope.runBlockingTest {
            //given
            val movieMockResponse = MovieListMockkModel.getMockMovieListResponse()
            coEvery {
                mockkDataSource.getPopularMovie(1)
            } returns MovieListMockkModel.getMockMovieListResponse()

            //when
            val callServiceResponse = repositoryImpl.getPopularMovie(1)

            //then
            Truth.assertThat(callServiceResponse).isEqualTo(movieMockResponse)
        }
    }

    @Test
    fun `when getPopularMovie() service call then should return Error response`() {
        runBlocking {
            //given
            val throwable = Exception()
            coEvery {
                mockkDataSource.getPopularMovie(10000)
            } throws throwable

            //when
            var callServiceResponse: MovieListModel? = null
            try {
                callServiceResponse = repositoryImpl.getPopularMovie(10000)
            } catch (ex: Exception) {
                Truth.assertThat(ex).isEqualTo(throwable)
            }

            //then
            Truth.assertThat(callServiceResponse).isEqualTo(null)
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
}