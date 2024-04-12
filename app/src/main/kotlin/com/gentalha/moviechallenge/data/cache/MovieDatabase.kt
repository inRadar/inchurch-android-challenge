package com.gentalha.moviechallenge.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gentalha.moviechallenge.data.cache.entity.MovieEntity

@Database(
    entities = [MovieEntity::class],
    version = 1
)
abstract class MovieDatabase : RoomDatabase() {

    abstract val movieDao: MovieDao
}