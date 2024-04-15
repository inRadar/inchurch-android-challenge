package com.example.movies.model

import com.example.movies.model.dtos.MovieDTO
import com.example.movies.model.dtos.MoviesDTO
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesService {

     @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("language") language: String = "pt-BR"
    ): MoviesDTO

    @GET("movie/{movie_id}")
    suspend fun getMovieById(
        @Path("movie_id") id: String,
        @Query("language") language: String = "pt-BR"
    ): MovieDTO

}