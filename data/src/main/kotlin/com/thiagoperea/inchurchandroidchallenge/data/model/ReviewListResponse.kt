package com.thiagoperea.inchurchandroidchallenge.data.model

import com.google.gson.annotations.SerializedName

data class ReviewListResponse(
    val results: List<ReviewResponse>
)

data class ReviewResponse(
    @SerializedName("author_details") val authorDetails: AuthorDetailsResponse,
    val content: String
)

data class AuthorDetailsResponse(
    val name: String,
    val rating: Double
)
