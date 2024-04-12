package com.gentalha.moviechallenge.data.remote.service

import com.gentalha.moviechallenge.data.remote.model.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("movie/popular")
    suspend fun getMovies(
        @Query("language") language: String = "pt-BR",
        @Query("page") page: Int = 1
    ): MoviesResponse
}