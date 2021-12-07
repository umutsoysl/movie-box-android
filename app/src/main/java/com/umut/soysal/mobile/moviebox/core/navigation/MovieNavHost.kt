package com.umut.soysal.mobile.moviebox.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.umut.soysal.mobile.moviebox.feature.MovieListScreen
import com.umut.soysal.mobile.moviebox.feature.splash.SplashScreen

@Composable
fun MovieNavHost() {
    val navigation = rememberNavController()

    NavHost(
        navController = navigation,
        startDestination = Page.SplashScreen.route) {
        //SplashScreen
        composable(
            route = Page.SplashScreen.route
        ) {
            SplashScreen( navigationController = navigation)
        }

        //MovieListScreen
        composable(
            route = Page.MovieListScreen.route
        ) {
            MovieListScreen()
        }
    }
}