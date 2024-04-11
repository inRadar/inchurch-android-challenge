package com.thiagoperea.inchurchandroidchallenge.data.datasource.remote

import com.thiagoperea.inchurchandroidchallenge.data.model.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBApi {

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("page") page: Int): MovieListResponse

}