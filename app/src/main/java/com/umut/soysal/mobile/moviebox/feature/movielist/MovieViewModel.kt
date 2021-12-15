package com.umut.soysal.mobile.moviebox.feature.movielist

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
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

    val pageStateFlow = MutableStateFlow(1)

    val loadingVisible = MutableStateFlow(false)

    val errorMessage = MutableStateFlow("")

    val movieList: State<MutableList<MovieModel>> = mutableStateOf(mutableListOf())

    fun getPopularMovie(page: Int = pageStateFlow.value) {
        viewModelScope.launch {
            loadingVisible.value = true
            when(val response = movieUseCase.getPopularMovie(page)) {
                is ResponseState.Error -> errorMessage.value = response.message
                is ResponseState.Success -> successMovieList(response.data)
                else -> loadingVisible.value = false
            }
        }
    }

    private fun successMovieList(movieListModel: MovieListModel) {
        pageStateFlow.value += 1
        movieList.value.addAll(movieListModel.results!!)
    }
}