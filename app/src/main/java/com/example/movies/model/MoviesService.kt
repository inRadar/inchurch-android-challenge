package com.example.movies.model

import com.example.movies.model.dtos.MoviesDTO
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MoviesService {

    @Headers("Accept: application/json", "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4MTRmZmI4ODc5ZTdkODdjZDJiZGE2ZDIyMmM4MjJiNyIsInN1YiI6IjY2MTk3NjI2YWYzZGE2MDE3YzE5MjhkZiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.c-uT5-9rTugbOEPrCGBs2rngQPcGH_AtCvv1GbJ6byc")
    @GET("popular")
    suspend fun getPopularMovies(
        @Query("language") language: String = "pt-BR"
    ): MoviesDTO

}