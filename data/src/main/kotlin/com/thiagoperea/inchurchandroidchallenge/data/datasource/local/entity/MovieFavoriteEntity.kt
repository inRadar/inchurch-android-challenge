package com.thiagoperea.inchurchandroidchallenge.data.datasource.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieFavoriteEntity(
    @PrimaryKey(autoGenerate = false) val movieId: Long,
    val title: String,
    val posterPath: String,
    val releaseDate: String,
    val genres: List<String>,
    val voteAverage: Double,
    val movieLenght: Int,
)