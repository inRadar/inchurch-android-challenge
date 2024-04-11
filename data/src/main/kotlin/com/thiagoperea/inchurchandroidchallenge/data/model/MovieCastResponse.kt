package com.thiagoperea.inchurchandroidchallenge.data.model

import com.google.gson.annotations.SerializedName

data class MovieCastResponse(
    val cast: List<CastResponse>,
)

data class CastResponse(
    val id: Long,
    val name: String,
    @SerializedName("profile_path") val profilePath: String,
)
