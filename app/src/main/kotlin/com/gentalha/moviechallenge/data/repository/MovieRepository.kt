package com.gentalha.moviechallenge.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.gentalha.moviechallenge.data.cache.MovieDatabase
import com.gentalha.moviechallenge.data.paging.mediator.MovieRemoteMediator
import com.gentalha.moviechallenge.data.remote.service.MovieService
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

private const val PAGE_SIZE = 20

class MovieRepository @Inject constructor(
    private val movieService: MovieService,
    private val movieDb: MovieDatabase
) {

    @OptIn(ExperimentalPagingApi::class)
    fun getMovies() = Pager(
        config = PagingConfig(
            pageSize = PAGE_SIZE,
            prefetchDistance = 15
        ),
        remoteMediator = MovieRemoteMediator(
            movieDb = movieDb,
            movieService = movieService
        ),
        pagingSourceFactory = {
            movieDb.movieDao.pagingSource()
        }
    ).flow

    fun getMovieDetail(id: Int) = flow {
        emit(movieService.getMovieDetail(id))
    }


}