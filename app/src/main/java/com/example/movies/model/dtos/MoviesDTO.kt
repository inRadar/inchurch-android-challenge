package com.example.movies.model.dtos

import com.google.gson.annotations.SerializedName


data class MoviesDTO(
    @SerializedName("results") val movies: List<MovieDTO>
)

data class MovieDTO(
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("backdrop_path") val backdropPath: String,
    var starred: Boolean = false
    )
