package com.gentalha.moviechallenge.data.paging.di

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.gentalha.moviechallenge.data.cache.MovieDatabase
import com.gentalha.moviechallenge.data.cache.entity.MovieEntity
import com.gentalha.moviechallenge.data.paging.mediator.MovieRemoteMediator
import com.gentalha.moviechallenge.data.remote.service.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val PAGE_SIZE = 20

@Module
@InstallIn(SingletonComponent::class)
class PagingModule {

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun provideMoviePager(movieDb: MovieDatabase, movieApi: MovieService): Pager<Int, MovieEntity> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                prefetchDistance = 10,
                initialLoadSize = PAGE_SIZE,
            ),
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