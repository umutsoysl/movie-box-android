package com.umut.soysal.mobile.moviebox.core.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.umut.soysal.mobile.moviebox.feature.movielist.MovieListScreen
import com.umut.soysal.mobile.moviebox.feature.splash.SplashScreen

@ExperimentalFoundationApi
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
            SplashScreen( navController = navigation)
        }

        //MovieListScreen
        composable(
            route = Page.MovieListScreen.route
        ) {
            MovieListScreen(navController = navigation)
        }
    }
}