package com.example.movies.model

class MoviesRepository(private val moviesService: MoviesService) {

    suspend fun getPopularMovies() = moviesService.getPopularMovies()
}