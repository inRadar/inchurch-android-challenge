package com.gentalha.moviechallenge.data.cache.di

import android.content.Context
import androidx.room.Room
import com.gentalha.moviechallenge.data.cache.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Provides
    @Singleton
    fun provideMovieDatabase(@ApplicationContext context: Context): MovieDatabase {
        return Room.databaseBuilder(context, MovieDatabase::class.java, "Movie-db")
            .build()
    }

    @Singleton
    @Provides
    fun provideMovieDao(database: MovieDatabase) = database.movieDao

}
