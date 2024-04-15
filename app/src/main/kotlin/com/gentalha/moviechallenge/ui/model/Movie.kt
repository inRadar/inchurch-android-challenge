package com.gentalha.moviechallenge.ui.model

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val releaseDate: String,
    val posterUrl: String,
    val backdropUrl: String,
    val isFavorite: Boolean
)
