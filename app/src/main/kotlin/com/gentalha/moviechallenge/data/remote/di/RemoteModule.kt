package com.gentalha.moviechallenge.data.remote.di

import com.gentalha.moviechallenge.data.remote.service.MovieService
import com.gentalha.moviechallenge.data.remote.util.ServiceBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val BASE_URL = "https://api.themoviedb.org/3"
@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {

    @Provides
    @Singleton
    fun provideMovieApiService() = ServiceBuilder().invoke<MovieService>(BASE_URL)
}