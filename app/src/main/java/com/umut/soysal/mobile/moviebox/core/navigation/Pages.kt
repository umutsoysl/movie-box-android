package com.umut.soysal.mobile.moviebox.core.navigation

sealed class Page(val route: String) {
    object SplashScreen : Page("splashScreen")
    object MovieListScreen : Page("movieListScreen")
}