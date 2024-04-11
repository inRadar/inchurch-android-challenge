package com.thiagoperea.inchurchandroidchallenge.data.datasource.remote

import com.thiagoperea.inchurchandroidchallenge.data.model.MovieCastResponse
import com.thiagoperea.inchurchandroidchallenge.data.model.MovieDetailsResponse
import com.thiagoperea.inchurchandroidchallenge.data.model.MovieListResponse
import com.thiagoperea.inchurchandroidchallenge.data.model.ReviewListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBApi {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int,
    ): MovieListResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Long,
    ): MovieDetailsResponse

    @GET("movie/{movie_id}/reviews")
    suspend fun getMovieReviews(
        @Path("movie_id") movieId: Long,
    ): ReviewListResponse

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCast(
        @Path("movie_id") movieId: Long,
    ): MovieCastResponse
}