package com.gentalha.moviechallenge.data.remote.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    val id: Int,
    val title: String,
    val overview: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    val isFavorite: Boolean = false
) {
    fun getPosterUrl() = "https://image.tmdb.org/t/p/w500$posterPath"
    fun getBackdropUrl() = "https://image.tmdb.org/t/p/w500$backdropPath"
}
