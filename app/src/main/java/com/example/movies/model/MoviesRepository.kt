package com.example.movies.model

import com.example.movies.model.dtos.MovieDTO
import com.example.movies.model.dtos.MoviesDTO

interface MoviesRepository {

    suspend fun getPopularMovies(): MoviesDTO

    suspend fun getMoviesById(ids: Set<String>): List<MovieDTO>


}