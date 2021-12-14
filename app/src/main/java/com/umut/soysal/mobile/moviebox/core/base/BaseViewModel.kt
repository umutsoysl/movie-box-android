package com.umut.soysal.mobile.moviebox.core.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel

abstract class BaseViewModel: ViewModel() {

    private val job = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + job)

    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
    }
}