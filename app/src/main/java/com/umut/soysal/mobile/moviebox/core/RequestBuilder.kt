package com.umut.soysal.mobile.moviebox.core

import com.umut.soysal.mobile.moviebox.core.constant.GlobalConstant
import okhttp3.HttpUrl
import okhttp3.Request

class RequestBuilder {
    companion object {
        fun build(request: Request): Request {
            val originalHttpUrl: HttpUrl = request.url
             val url =  originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", GlobalConstant.API_KEY)
                .addQueryParameter("language", GlobalConstant.SERVICE_LANGUAGE)
                 .build()

            return request.newBuilder().url(url).build()
        }
    }
}