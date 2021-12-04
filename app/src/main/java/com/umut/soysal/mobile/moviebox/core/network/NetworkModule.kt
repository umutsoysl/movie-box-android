package com.umut.soysal.mobile.moviebox.core.network

import android.content.Context
import com.squareup.moshi.Moshi
import com.umut.soysal.mobile.moviebox.core.MovieBoxApplication
import com.umut.soysal.mobile.moviebox.core.RequestBuilder
import com.umut.soysal.mobile.moviebox.core.constant.GlobalConstant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): MovieBoxApplication {
        return app as MovieBoxApplication
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(GlobalConstant.BASE_URL).client(client)
            .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()))
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        headerInterceptor: Interceptor,
        cache: Cache
    ): OkHttpClient {

        val okHttpClientBuilder = OkHttpClient().newBuilder()
        okHttpClientBuilder.connectTimeout(GlobalConstant.CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
        okHttpClientBuilder.readTimeout(GlobalConstant.READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
        okHttpClientBuilder.writeTimeout(GlobalConstant.WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
        okHttpClientBuilder.cache(cache)
        okHttpClientBuilder.addInterceptor(headerInterceptor)

        return okHttpClientBuilder.build()
    }


    @Provides
    @Singleton
    fun provideInterceptor(): Interceptor {
        return Interceptor {chain->
            val original = chain.request()
            val request = RequestBuilder.build(original)
            chain.proceed(request)
        }
    }


    @Provides
    @Singleton
    internal fun provideCache(context: Context): Cache {
        val httpCacheDirectory = File(context.cacheDir.absolutePath, "HttpCache")
        return Cache(httpCacheDirectory, GlobalConstant.CACHE_SIZE_BYTES)
    }


    @Provides
    @Singleton
    fun provideContext(application: MovieBoxApplication): Context {
        return application.applicationContext
    }
}