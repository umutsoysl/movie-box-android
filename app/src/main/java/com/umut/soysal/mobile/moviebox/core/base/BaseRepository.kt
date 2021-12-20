package com.umut.soysal.mobile.moviebox.core.base

import android.content.Context
import com.umut.soysal.mobile.moviebox.core.helper.SystemNetworkHelper
import com.umut.soysal.mobile.moviebox.core.ui.state.ResponseState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import okio.IOException
import retrofit2.HttpException

suspend fun <T> safeApiCall(context: Context, dispatcher: CoroutineDispatcher, apiCall: suspend () -> T): Flow<ResponseState<T>> = flow {
    emit(ResponseState.Loading)
    if(SystemNetworkHelper.isNetworkConnection(context)) {
        emit(ResponseState.Success(apiCall.invoke()))
    } else {
        emit(ResponseState.Error("0", "Internet Baglantınızı kontrol ediniz!"))
    }
}.catch {
    when (it) {
        is java.io.IOException -> emit(ResponseState.Error("1", it.message.toString()))
        is HttpException -> {
            emit(ResponseState.Error("2",  it.response()?.errorBody().toString()))
        }
        else -> {
            emit(ResponseState.Error("3", "İşleminiz yapılırken anlık hata alındı"))
        }
    }
}.flowOn(dispatcher)