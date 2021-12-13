package com.umut.soysal.mobile.moviebox.feature.movielist

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umut.soysal.mobile.moviebox.core.base.BaseViewModel
import com.umut.soysal.mobile.moviebox.core.ui.state.ResponseState
import com.umut.soysal.mobile.moviebox.data.remote.model.MovieListModel
import com.umut.soysal.mobile.moviebox.data.remote.model.MovieModel
import com.umut.soysal.mobile.moviebox.data.remote.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase
): BaseViewModel() {

    private val responseState = mutableStateOf<ResponseState<MovieListModel>>(ResponseState.Loading)

    fun getResponse(): State<ResponseState<MovieListModel>> = responseState

    val pageStateFlow = MutableStateFlow(1)

    val movieList: State<MutableList<MovieModel>> = mutableStateOf(mutableListOf())

    fun getPopularMovie(page: Int = pageStateFlow.value) {
        responseState.value = ResponseState.Loading
        viewModelScope.launch {
            try {
                responseState.value = ResponseState.Success(
                    movieUseCase.getPopularMovie(page)
                )
                pageStateFlow.value += 1
            } catch (e: Exception) {
                responseState.value = ResponseState.Error(e.message!!)
            }
        }
    }
}