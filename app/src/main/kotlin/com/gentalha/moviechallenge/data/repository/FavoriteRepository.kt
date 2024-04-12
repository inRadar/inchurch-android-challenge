package com.gentalha.moviechallenge.data.repository

import com.gentalha.moviechallenge.data.cache.dao.MovieDao
import com.gentalha.moviechallenge.data.cache.entity.MovieEntity
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FavoriteRepository @Inject constructor(
    private val movieDao: MovieDao
) {

    fun getMovieBy(title: String) = flow {
        emit(movieDao.searchBy(title))
    }

    suspend fun addFavorite(movie: MovieEntity) = movieDao.update(movie)

    fun getFavorites() = flow {
        emit(movieDao.getFavorites())
    }

}