package com.thiagoperea.inchurchandroidchallenge.data.model

import com.google.gson.annotations.SerializedName

data class MovieDetailsResponse(
    val id: Long,
    val title: String,
    val overview: String,
    val genres: List<GenreResponse>,
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("vote_average") val voteAverage: Float,
    @SerializedName("release_date") val releaseDate: String,
    val runtime: Int
)

data class GenreResponse(
    val id: Long,
    val name: String
)