package com.gentalha.moviechallenge.data.cache

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.gentalha.moviechallenge.data.cache.entity.MovieEntity

@Dao
interface MovieDao {

    @Upsert
    suspend fun upsertAll(movies: List<MovieEntity>)

    @Query("SELECT * FROM MovieEntity")
    fun pagingSource(): PagingSource<Int, MovieEntity>

    @Query("DELETE FROM MovieEntity")
    suspend fun clearAll()
}