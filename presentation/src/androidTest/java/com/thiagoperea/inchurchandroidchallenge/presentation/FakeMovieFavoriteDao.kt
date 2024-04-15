package com.thiagoperea.inchurchandroidchallenge.presentation

import com.thiagoperea.inchurchandroidchallenge.data.datasource.local.dao.MovieFavoriteDao
import com.thiagoperea.inchurchandroidchallenge.data.datasource.local.entity.MovieFavoriteEntity

class FakeMovieFavoriteDao : MovieFavoriteDao {

    val movieFavorites = mutableListOf<MovieFavoriteEntity>()

    override suspend fun insert(movieFavorite: MovieFavoriteEntity) {
        movieFavorites.add(movieFavorite)
    }

    override suspend fun delete(movieId: Long) {
        movieFavorites.removeIf { it.movieId == movieId }
    }

    override suspend fun getAll(): List<MovieFavoriteEntity> {
        return movieFavorites
    }

    override suspend fun isFavorite(movieId: Long): MovieFavoriteEntity? {
        return movieFavorites.find { it.movieId == movieId }
    }
}