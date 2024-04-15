package com.gentalha.moviechallenge.data.cache.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.gentalha.moviechallenge.data.cache.entity.MovieEntity

@Dao
interface MovieDao {

    @Upsert
    suspend fun upsert(movieEntity: MovieEntity)

    @Query("SELECT * FROM movies WHERE isFavorite = 1")
    suspend fun getFavorites(): List<MovieEntity>

    @Query("SELECT * FROM movies WHERE isFavorite = 1 AND title LIKE '%' || :title || '%'")
    suspend fun searchBy(title: String): List<MovieEntity>
}