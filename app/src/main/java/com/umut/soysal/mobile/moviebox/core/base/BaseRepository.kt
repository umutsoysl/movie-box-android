package com.umut.soysal.mobile.moviebox.core.base

import com.umut.soysal.mobile.moviebox.core.ui.state.ResponseState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import okio.IOException
import retrofit2.HttpException

suspend fun <T> safeApiCall(dispatcher: CoroutineDispatcher, apiCall: suspend () -> T): ResponseState<T> {
        return withContext(dispatcher) {
            try {
                ResponseState.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is IOException -> ResponseState.Error("0", throwable.message.toString())
                    is HttpException -> {
                        ResponseState.Error("1", throwable.response()?.errorBody().toString())
                    }
                    else -> {
                        ResponseState.Error("2","İşleminiz yapılırken anlık hata alındı")
                    }
                }
            }
        }
    }
