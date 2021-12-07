package com.umut.soysal.mobile.moviebox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.umut.soysal.mobile.moviebox.core.navigation.MovieNavHost
import com.umut.soysal.mobile.moviebox.core.ui.theme.MovieBoxTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieBoxTheme {
                MovieNavHost()
            }
        }
    }
}