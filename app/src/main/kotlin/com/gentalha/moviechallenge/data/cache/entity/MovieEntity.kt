package com.gentalha.moviechallenge.data.cache.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val overview: String,
    val releaseDate: String,
    val posterUrl: String,
    val isFavorite: Boolean,
    @ColumnInfo(name = "page")
    var page: Int = 0,
)
