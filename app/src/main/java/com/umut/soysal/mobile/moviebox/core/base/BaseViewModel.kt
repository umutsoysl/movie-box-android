package com.umut.soysal.mobile.moviebox.core.base

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.umut.soysal.mobile.moviebox.core.ui.state.ResponseState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel

abstract class BaseViewModel: ViewModel() {

    private val job = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + job)

    protected val response = mutableStateOf<ResponseState<BaseResponse>>(ResponseState.Loading)

    var callBackStateFlow: State<ResponseState<BaseResponse>> = response

    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
    }
}