package com.umut.soysal.mobile.moviebox.feature

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umut.soysal.mobile.moviebox.core.ui.state.ResponseState
import com.umut.soysal.mobile.moviebox.data.remote.model.MovieListModel
import com.umut.soysal.mobile.moviebox.data.remote.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase
): ViewModel() {

    private val responseState = mutableStateOf<ResponseState<MovieListModel>>(ResponseState.Loading)

    fun getResponse(): State<ResponseState<MovieListModel>> = responseState

    fun getPopularMovie(page: Int) {
        viewModelScope.launch {
            responseState.value = ResponseState.Loading
            try {
                responseState.value = ResponseState.Success(
                    movieUseCase.getPopularMovie(page)
                )
            } catch (e: Exception) {
                responseState.value = ResponseState.Error("Şuanda işlem gerceklesmiyor.")
            }
        }
    }
}