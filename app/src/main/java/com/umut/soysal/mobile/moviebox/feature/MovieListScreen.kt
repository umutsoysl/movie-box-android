package com.umut.soysal.mobile.moviebox.feature

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.umut.soysal.mobile.moviebox.core.loading.LoadingScreen
import com.umut.soysal.mobile.moviebox.core.ui.state.ResponseState
import com.umut.soysal.mobile.moviebox.data.remote.model.MovieListModel

@Composable
fun MovieListScreen(movieViewModel: MovieViewModel = hiltViewModel()) {
    val responseState by movieViewModel.getResponse()

    when(responseState) {
        is ResponseState.Success -> {
            val data = (responseState as ResponseState.Success<MovieListModel>).data

        }
        is ResponseState.Loading -> {
            LoadingScreen()
        }
        is ResponseState.Error -> TODO()
    }

    LaunchedEffect(Unit) {
        movieViewModel.getPopularMovie(1)
    }
}

@Preview(showBackground = true)
@Composable
private fun MovieListScreenPreview() {
    MovieListScreen()
}