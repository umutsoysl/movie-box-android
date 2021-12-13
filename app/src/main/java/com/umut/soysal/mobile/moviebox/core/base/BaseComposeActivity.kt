package com.umut.soysal.mobile.moviebox.core.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import dagger.hilt.android.AndroidEntryPoint


@ExperimentalFoundationApi
@AndroidEntryPoint
abstract class BaseComposeActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {}
    }
}