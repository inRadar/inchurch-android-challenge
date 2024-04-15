package com.thiagoperea.inchurchandroidchallenge.data.repository

import com.thiagoperea.inchurchandroidchallenge.data.datasource.local.dao.MovieFavoriteDao
import com.thiagoperea.inchurchandroidchallenge.data.datasource.local.entity.MovieFavoriteEntity
import com.thiagoperea.inchurchandroidchallenge.data.datasource.remote.TMDBApi
import com.thiagoperea.inchurchandroidchallenge.data.model.MovieCastResponse
import com.thiagoperea.inchurchandroidchallenge.data.model.MovieDetailsResponse
import com.thiagoperea.inchurchandroidchallenge.data.model.MovieDetailsUiModel
import com.thiagoperea.inchurchandroidchallenge.data.model.MovieListResponse
import com.thiagoperea.inchurchandroidchallenge.data.model.ReviewListResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MovieRepositoryTest {

    private val testDispatcher = UnconfinedTestDispatcher()

    @MockK(relaxed = true)
    private lateinit var tmdbApiMock: TMDBApi

    @MockK(relaxed = true)
    private lateinit var movieFavoriteDaoMock: MovieFavoriteDao

    private lateinit var repository: MovieRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repository = MovieRepository(
            api = tmdbApiMock,
            dao = movieFavoriteDaoMock,
            dispatcher = testDispatcher
        )
    }

    @Test
    fun `test getMovieList success`() = runTest {
        val response = MovieListResponse(0, 0, listOf())
        coEvery { tmdbApiMock.getPopularMovies(1) } returns response

        val result = repository.getMovieList()

        assertTrue(result.isSuccess)
        assertEquals(response, result.getOrNull())
    }

    @Test
    fun `test getMovieList failure`() = runTest {
        val exception = Exception("error")
        coEvery { tmdbApiMock.getPopularMovies(1) } throws exception

        val result = repository.getMovieList()

        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }

    @Test
    fun `test getMovieDetails success`() = runTest {
        val movieId = 1L
        val responseDetails = MovieDetailsResponse(0, "", "", listOf(), "", "", 0.0, "", 0)
        val responseReviews = ReviewListResponse(listOf())
        val responseCast = MovieCastResponse(listOf())
        coEvery { tmdbApiMock.getMovieDetails(movieId) } returns responseDetails
        coEvery { tmdbApiMock.getMovieReviews(movieId) } returns responseReviews
        coEvery { tmdbApiMock.getMovieCast(movieId) } returns responseCast

        val result = repository.getMovieDetails(movieId)

        assertTrue(result.isSuccess)
        assertEquals(result.getOrNull()!!.details, responseDetails)
        assertEquals(result.getOrNull()!!.reviews, responseReviews)
        assertEquals(result.getOrNull()!!.cast, responseCast)
    }

    @Test
    fun `test getMovieDetails failure`() = runTest {
        val movieId = 1L
        val exception = Exception("error")
        coEvery { tmdbApiMock.getMovieDetails(movieId) } throws exception

        val result = repository.getMovieDetails(movieId)

        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }

    @Test
    fun `test isMovieFavorite`() = runTest {
        val movieId = 1L
        coEvery { movieFavoriteDaoMock.isFavorite(movieId) } returns MovieFavoriteEntity(0L, "", "", "", listOf(), 0.0, 0)

        val result = repository.isMovieFavorite(movieId)

        assertTrue(result)
    }

    @Test
    fun `test getFavoriteMovies success`() = runTest {
        val response = listOf(MovieFavoriteEntity(0L, "", "", "", listOf(), 0.0, 0))
        coEvery { movieFavoriteDaoMock.getAll() } returns response

        val result = repository.getFavoriteMovies()

        assertTrue(result.isSuccess)
        assertEquals(response, result.getOrNull())
    }

    @Test
    fun `test getFavoriteMovies failure`() = runTest {
        val exception = Exception("error")
        coEvery { movieFavoriteDaoMock.getAll() } throws exception

        val result = repository.getFavoriteMovies()

        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }

    @Test
    fun `test saveFavorite`() = runTest {
        val movieUiModel = MovieDetailsUiModel(
            MovieDetailsResponse(0, "", "", listOf(), "", "", 0.0, "", 0),
            ReviewListResponse(listOf()),
            MovieCastResponse(listOf())
        )

        repository.saveFavorite(movieUiModel)

        coVerify { movieFavoriteDaoMock.insert(any()) }
    }

    @Test
    fun `test removeFavorite`() = runTest {
        val movieId = 1L

        repository.removeFavorite(movieId)

        coVerify { movieFavoriteDaoMock.delete(movieId) }
    }
}