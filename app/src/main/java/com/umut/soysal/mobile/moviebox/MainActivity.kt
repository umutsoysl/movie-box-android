package com.umut.soysal.mobile.moviebox

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import com.umut.soysal.mobile.moviebox.core.base.BaseComposeActivity
import com.umut.soysal.mobile.moviebox.core.navigation.MovieNavHost
import com.umut.soysal.mobile.moviebox.core.ui.theme.MovieBoxTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalFoundationApi
@AndroidEntryPoint
class MainActivity : BaseComposeActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieBoxTheme {
                MovieNavHost()
            }
        }
    }
}