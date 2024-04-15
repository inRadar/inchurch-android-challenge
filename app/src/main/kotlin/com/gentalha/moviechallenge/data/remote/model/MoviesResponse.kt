package com.gentalha.moviechallenge.data.remote.model

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    @SerializedName("results")
    val movies: List<MovieResponse>,
    @SerializedName("page")
    val page: Int? = null
)
