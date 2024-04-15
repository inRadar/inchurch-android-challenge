package com.gentalha.moviechallenge.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.gentalha.moviechallenge.data.cache.dao.MovieDao
import com.gentalha.moviechallenge.data.paging.MoviePagingSource
import com.gentalha.moviechallenge.data.remote.service.MovieService
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

private const val PAGE_SIZE = 20

class MovieRepository @Inject constructor(
    private val movieService: MovieService,
    private val movieDao: MovieDao
) {

    fun getMovies() = Pager(
        config = PagingConfig(
            pageSize = PAGE_SIZE,
            prefetchDistance = 2
        ),
        pagingSourceFactory = {
            MoviePagingSource(movieService, movieDao)
        }
    ).flow

    fun getMovieDetail(id: Int) = flow {
        emit(movieService.getMovieDetail(id))
    }


}