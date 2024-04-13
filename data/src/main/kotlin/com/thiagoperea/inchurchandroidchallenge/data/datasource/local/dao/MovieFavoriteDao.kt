package com.thiagoperea.inchurchandroidchallenge.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thiagoperea.inchurchandroidchallenge.data.datasource.local.entity.MovieFavoriteEntity

@Dao
interface MovieFavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movieFavorite: MovieFavoriteEntity)

    @Query("DELETE FROM MovieFavoriteEntity WHERE movieId = :movieId")
    suspend fun delete(movieId: Long)

    @Query("SELECT * FROM MovieFavoriteEntity")
    suspend fun getAll(): List<MovieFavoriteEntity>

    @Query("SELECT * FROM MovieFavoriteEntity WHERE movieId = :movieId")
    suspend fun isFavorite(movieId: Long): MovieFavoriteEntity?
}