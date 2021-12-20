package com.umut.soysal.mobile.moviebox.feature.movielist

import androidx.annotation.VisibleForTesting
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.umut.soysal.mobile.moviebox.core.base.BaseViewModel
import com.umut.soysal.mobile.moviebox.core.di.IoDispatcher
import com.umut.soysal.mobile.moviebox.core.ui.state.ResponseState
import com.umut.soysal.mobile.moviebox.data.remote.model.MovieListModel
import com.umut.soysal.mobile.moviebox.data.remote.model.MovieModel
import com.umut.soysal.mobile.moviebox.data.remote.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): BaseViewModel() {

    val pageStateFlow = MutableStateFlow(1)

    @VisibleForTesting
    val movieList: State<MutableList<MovieModel>> = mutableStateOf(mutableListOf())

    fun getPopularMovie(page: Int = pageStateFlow.value) {
       flow<ResponseState<MovieListModel>>{
           movieUseCase.getPopularMovie(page).collect {
                response.value = it
           }
       }.flowOn(ioDispatcher).stateIn(
           viewModelScope,
           SharingStarted.Eagerly,
           ResponseState.Loading
       )
    }
}