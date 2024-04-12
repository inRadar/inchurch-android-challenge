package com.gentalha.moviechallenge.data.remote.di

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.gentalha.moviechallenge.data.cache.MovieDatabase
import com.gentalha.moviechallenge.data.cache.entity.MovieEntity
import com.gentalha.moviechallenge.data.remote.mediator.MovieRemoteMediator
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

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun provideMoviePager(movieDb: MovieDatabase, movieApi: MovieService): Pager<Int, MovieEntity> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = MovieRemoteMediator(
                movieDb = movieDb,
                movieService = movieApi
            ),
            pagingSourceFactory = {
                movieDb.movieDao.pagingSource()
            }
        )
    }
}