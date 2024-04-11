package com.thiagoperea.inchurchandroidchallenge.data.model

import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    val page: Int,
    @SerializedName("total_pages") val totalPages: Int,
    val results: List<MovieResponse>
)

data class MovieResponse(
    val id: Long,
    @SerializedName("poster_path") val posterPath: String
)
