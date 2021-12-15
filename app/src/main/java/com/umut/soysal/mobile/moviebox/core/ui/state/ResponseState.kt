package com.umut.soysal.mobile.moviebox.core.ui.state


sealed class ResponseState<out T> {

    class Success<T>(val data: T) : ResponseState<T>()

    class Error(val errorType: String, val message: String) : ResponseState<Nothing>()

    object Loading : ResponseState<Nothing>()
}