package com.umut.soysal.mobile.moviebox.feature.movielist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.umut.soysal.mobile.moviebox.components.MoviePosterComponent
import com.umut.soysal.mobile.moviebox.core.error.ErrorScreen
import com.umut.soysal.mobile.moviebox.core.extensions.paging
import com.umut.soysal.mobile.moviebox.core.loading.LoadingScreen
import com.umut.soysal.mobile.moviebox.core.ui.state.ResponseState
import com.umut.soysal.mobile.moviebox.data.remote.model.MovieModel

@ExperimentalFoundationApi
@Composable
fun MovieListScreen(navController: NavController? = null, movieViewModel: MovieViewModel = hiltViewModel()) {
    MovieList(movieViewModel)
    
    if(movieViewModel.loadingVisible.value) {
        LoadingScreen()
    }
    
    if(!movieViewModel.errorMessage.value.isNullOrEmpty()) {
        ErrorScreen(message = movieViewModel.errorMessage.value)
    }
    
    LaunchedEffect(Unit) {
        movieViewModel.getPopularMovie()
    }
}


@ExperimentalFoundationApi
@Composable
private fun MovieList(movieViewModel: MovieViewModel) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(3),
    ){
        paging(
            items = movieViewModel.movieList.value as List<MovieModel>,
            currentIndexFlow = movieViewModel.pageStateFlow,
            pageSize = movieViewModel.movieList.value.size,
            fetch = {movieViewModel.getPopularMovie()}
        ) {
            MoviePosterComponent(movie = it)
        }
    }
}