package com.thiagoperea.inchurchandroidchallenge.data.repository

import com.thiagoperea.inchurchandroidchallenge.data.datasource.local.dao.MovieFavoriteDao
import com.thiagoperea.inchurchandroidchallenge.data.datasource.local.entity.MovieFavoriteEntity
import com.thiagoperea.inchurchandroidchallenge.data.datasource.remote.TMDBApi
import com.thiagoperea.inchurchandroidchallenge.data.model.MovieDetailsUiModel
import com.thiagoperea.inchurchandroidchallenge.data.model.MovieListResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepository(
    private val api: TMDBApi,
    private val dao: MovieFavoriteDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
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

    suspend fun isMovieFavorite(movieId: Long): Boolean {
        return withContext(dispatcher) {
            dao.isFavorite(movieId) != null
        }
    }

    suspend fun getFavoriteMovies(): Result<List<MovieFavoriteEntity>> {
        try {
            val favorites = withContext(dispatcher) { dao.getAll() }

            return Result.success(favorites)
        } catch (error: Exception) {
            return Result.failure(error)
        }
    }

    suspend fun saveFavorite(movieUiModel: MovieDetailsUiModel?) {
        val details = movieUiModel?.details ?: return

        val modelToSave = MovieFavoriteEntity(
            details.id,
            details.title,
            details.posterPath,
            details.releaseDate,
            details.genres.map { it.name },
            details.voteAverage,
            details.runtime
        )

        withContext(dispatcher) { dao.insert(modelToSave) }
    }

    suspend fun removeFavorite(movieId: Long) {
        withContext(dispatcher) { dao.delete(movieId) }
    }
}