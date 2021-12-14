package com.umut.soysal.mobile.moviebox.data.di

import com.umut.soysal.mobile.moviebox.data.remote.repository.MovieRepository
import com.umut.soysal.mobile.moviebox.data.remote.repository.MovieRepositoryImp
import com.umut.soysal.mobile.moviebox.data.remote.service.MovieService
import com.umut.soysal.mobile.moviebox.data.remote.usecase.MovieUseCase
import com.umut.soysal.mobile.moviebox.data.remote.usecase.MovieUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
abstract class MovieDIModule {
    @Binds
    abstract fun provideMovieRepository(
        movieRepositoryImp: MovieRepositoryImp
    ): MovieRepository

    @Binds
    abstract fun provideMovieUseCase(
        movieInformationUseCaseImpl: MovieUseCaseImpl
    ): MovieUseCase

    companion object {

        @Provides
        fun provideMovieService(
            retrofit: Retrofit
        ): MovieService = retrofit.create(MovieService::class.java)
    }
}