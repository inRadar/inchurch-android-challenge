package com.thiagoperea.inchurchandroidchallenge.data.repository

import com.thiagoperea.inchurchandroidchallenge.data.datasource.remote.TMDBApi
import com.thiagoperea.inchurchandroidchallenge.data.model.MovieDetailsUiModel
import com.thiagoperea.inchurchandroidchallenge.data.model.MovieListResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepository(
    val api: TMDBApi,
    val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun getMovieList(): Result<MovieListResponse> {
        // page number will be handled later

        try {
            val response = withContext(dispatcher) { api.getPopularMovies(1) }
            return Result.success(response)
        } catch (error: Exception) {
            return Result.failure(error)
        }
    }

    suspend fun getMovieDetails(movieId: Long): Result<MovieDetailsUiModel> {
        try {
            val details = withContext(dispatcher) { api.getMovieDetails(movieId) }
            val reviews = withContext(dispatcher) { api.getMovieReviews(movieId) }
            val cast = withContext(dispatcher) { api.getMovieCast(movieId) }

            return Result.success(
                MovieDetailsUiModel(details, reviews, cast)
            )
        } catch (error: Exception) {
            return Result.failure(error)
        }
    }
}