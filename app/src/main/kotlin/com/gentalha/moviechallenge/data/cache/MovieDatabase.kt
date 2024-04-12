package com.gentalha.moviechallenge.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gentalha.moviechallenge.data.cache.dao.MovieDao
import com.gentalha.moviechallenge.data.cache.dao.RemoteKeysDao
import com.gentalha.moviechallenge.data.cache.entity.MovieEntity
import com.gentalha.moviechallenge.data.cache.entity.RemoteKeys

@Database(
    entities = [MovieEntity::class, RemoteKeys::class],
    version = 1
)
abstract class MovieDatabase : RoomDatabase() {

    abstract val movieDao: MovieDao

    abstract val remoteKeysDao: RemoteKeysDao
}