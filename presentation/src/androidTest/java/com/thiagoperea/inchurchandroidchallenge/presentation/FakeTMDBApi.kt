package com.thiagoperea.inchurchandroidchallenge.presentation

import com.thiagoperea.inchurchandroidchallenge.data.datasource.remote.TMDBApi
import com.thiagoperea.inchurchandroidchallenge.data.model.AuthorDetailsResponse
import com.thiagoperea.inchurchandroidchallenge.data.model.CastResponse
import com.thiagoperea.inchurchandroidchallenge.data.model.GenreResponse
import com.thiagoperea.inchurchandroidchallenge.data.model.MovieCastResponse
import com.thiagoperea.inchurchandroidchallenge.data.model.MovieDetailsResponse
import com.thiagoperea.inchurchandroidchallenge.data.model.MovieListResponse
import com.thiagoperea.inchurchandroidchallenge.data.model.MovieResponse
import com.thiagoperea.inchurchandroidchallenge.data.model.ReviewListResponse
import com.thiagoperea.inchurchandroidchallenge.data.model.ReviewResponse

class FakeTMDBApi : TMDBApi {

    override suspend fun getPopularMovies(page: Int): MovieListResponse {
        val movies = mutableListOf<MovieResponse>()

        repeat(20) {
            movies.add(MovieResponse(it.toLong(), "/poster$it"))
        }

        return MovieListResponse(
            page = 1,
            totalPages = 1,
            results = movies
        )
    }

    override suspend fun getMovieDetails(movieId: Long): MovieDetailsResponse {
        return MovieDetailsResponse(
            id = movieId,
            title = "Movie $movieId",
            posterPath = "/poster$movieId",
            backdropPath = "/backdrop$movieId",
            overview = "Overview $movieId",
            releaseDate = "2022-01-01",
            voteAverage = 7.5,
            genres = listOf(
                GenreResponse(1, "Action"),
                GenreResponse(2, "Adventure")
            ),
            runtime = 120
        )
    }

    override suspend fun getMovieReviews(movieId: Long): ReviewListResponse {
        val reviews = mutableListOf<ReviewResponse>()

        repeat(5) {
            reviews.add(
                ReviewResponse(
                    authorDetails = AuthorDetailsResponse("Author $it", it.toDouble()),
                    content = "Content $it"
                )
            )
        }

        return ReviewListResponse(
            results = reviews
        )
    }

    override suspend fun getMovieCast(movieId: Long): MovieCastResponse {
        val cast = mutableListOf<CastResponse>()

        repeat(5) {
            cast.add(
                CastResponse(
                    id = it.toLong(),
                    name = "Actor $it",
                    profilePath = "/profile$it"
                )
            )
        }

        return MovieCastResponse(
            cast = cast
        )
    }
}