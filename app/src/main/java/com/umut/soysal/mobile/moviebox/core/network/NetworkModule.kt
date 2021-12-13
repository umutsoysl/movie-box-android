package com.umut.soysal.mobile.moviebox.core.network

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.umut.soysal.mobile.moviebox.core.base.MovieBoxApplication
import com.umut.soysal.mobile.moviebox.core.base.RequestBuilder
import com.umut.soysal.mobile.moviebox.core.constant.GlobalConstant
import com.umut.soysal.mobile.moviebox.core.helper.SystemNetworkHelper
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
    fun provideKotlinJsonAdapterFactory(): KotlinJsonAdapterFactory = KotlinJsonAdapterFactory()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, moshiConverterFactory: MoshiConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(GlobalConstant.BASE_URL).client(client)
            .addConverterFactory(moshiConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideMoshiConverterFactory(moshi: Moshi): MoshiConverterFactory =
        MoshiConverterFactory.create(moshi)

    @Provides
    @Singleton
    fun provideMoshi(kotlinJsonAdapterFactory: KotlinJsonAdapterFactory): Moshi = Moshi.Builder()
        .add(kotlinJsonAdapterFactory)
        .build()

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
    fun provideInterceptor(isNetworkConnection: Boolean): Interceptor {
        return Interceptor {chain->
            val original = chain.request()
            val request = RequestBuilder.build(original)
            if(!isNetworkConnection) {
               throw Exception("Check Network Connection")
            }
            chain.proceed(request)
        }
    }

    @Provides
    @Singleton
    internal fun provideIsNetworkConnection(application: MovieBoxApplication): Boolean {
        return SystemNetworkHelper.isNetworkConnection(application)
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