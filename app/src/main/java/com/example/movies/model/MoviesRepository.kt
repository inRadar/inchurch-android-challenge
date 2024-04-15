package com.example.movies.model

import com.example.movies.model.dtos.MovieDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext

class MoviesRepository(private val moviesService: MoviesService) {

    suspend fun getPopularMovies() = moviesService.getPopularMovies()

    suspend fun getMoviesById(ids: Set<String>): List<MovieDTO> = withContext(Dispatchers.IO) {
        val movies: ArrayList<MovieDTO> = arrayListOf()
        ids.map { async {
            movies.add(moviesService.getMovieById(it))
        } }.awaitAll()

        return@withContext movies
    }

}