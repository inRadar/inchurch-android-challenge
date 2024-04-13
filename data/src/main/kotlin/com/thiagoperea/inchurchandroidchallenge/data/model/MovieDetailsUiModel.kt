package com.thiagoperea.inchurchandroidchallenge.data.model

data class MovieDetailsUiModel(
    var details: MovieDetailsResponse? = null,
    var reviews: ReviewListResponse? = null,
    var cast: MovieCastResponse? = null
)