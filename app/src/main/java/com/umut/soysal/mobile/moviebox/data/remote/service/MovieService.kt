package com.umut.soysal.mobile.moviebox.data.remote.service

import com.umut.soysal.mobile.moviebox.data.remote.model.MovieListModel
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("/3/movie/popular")
    suspend fun getPopularMovie(@Query("page") page: Int): MovieListModel
}