package com.gentalha.moviechallenge.data.cache.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.gentalha.moviechallenge.data.cache.entity.MovieEntity

@Dao
interface MovieDao {

    @Upsert
    suspend fun upsertAll(movies: List<MovieEntity>)

    @Query("SELECT * FROM movies")
    fun pagingSource(): PagingSource<Int, MovieEntity>

    @Query("DELETE FROM movies")
    suspend fun clearAll()

    @Query("SELECT * FROM movies WHERE isFavorite = 1")
    suspend fun getFavorites(): List<MovieEntity>

    @Query("SELECT * FROM movies WHERE title LIKE '%' || :title || '%'")
    suspend fun searchBy(title: String): List<MovieEntity>

    @Update
    suspend fun update(exchangeEntity: MovieEntity)
}