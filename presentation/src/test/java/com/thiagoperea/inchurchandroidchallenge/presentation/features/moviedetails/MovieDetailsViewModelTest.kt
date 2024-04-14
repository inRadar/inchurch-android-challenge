package com.thiagoperea.inchurchandroidchallenge.presentation.features.moviedetails

import com.thiagoperea.inchurchandroidchallenge.data.model.MovieDetailsUiModel
import com.thiagoperea.inchurchandroidchallenge.data.repository.MovieRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MovieDetailsViewModelTest {

    private val testDispatcher = UnconfinedTestDispatcher()

    @MockK(relaxed = true)
    private lateinit var repositoryMock: MovieRepository

    lateinit var viewModel: MovieDetailsViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        MockKAnnotations.init(this)
        viewModel = MovieDetailsViewModel(repositoryMock)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test getMovieDetails success`() {
        // given
        val movieId = 1L
        val movieDetails = MovieDetailsUiModel()
        coEvery { repositoryMock.getMovieDetails(movieId) } returns Result.success(movieDetails)
        coEvery { repositoryMock.isMovieFavorite(movieId) } returns true

        // when
        viewModel.getMovieDetails(movieId)

        // then
        assertTrue(viewModel.uiState.value is MovieDetailsState.Success)
        assertEquals((viewModel.uiState.value as MovieDetailsState.Success).data, movieDetails)
        assertTrue((viewModel.uiState.value as MovieDetailsState.Success).isMovieFavorite)
    }

    @Test
    fun `test getMovieDetails error`() {
        // given
        val movieId = 1L
        val error = Exception("error")
        coEvery { repositoryMock.getMovieDetails(movieId) } returns Result.failure(error)

        // when
        viewModel.getMovieDetails(movieId)

        // then
        assertTrue(viewModel.uiState.value is MovieDetailsState.Error)
        assertEquals((viewModel.uiState.value as MovieDetailsState.Error).message, error.message)
    }

    @Test
    fun `test toggleFavorite true`() {
        // given
        val movieId = 1L
        coEvery { repositoryMock.isMovieFavorite(movieId) } returns true

        // when
        viewModel.toggleFavorite(movieId, true)

        // then
        coVerify { repositoryMock.saveFavorite(any()) }
        assertTrue(viewModel.uiState.value is MovieDetailsState.Success)
        assertTrue((viewModel.uiState.value as MovieDetailsState.Success).isMovieFavorite)
    }

    @Test
    fun `test toggleFavorite false`() {
        // given
        val movieId = 1L
        coEvery { repositoryMock.isMovieFavorite(movieId) } returns false

        // when
        viewModel.toggleFavorite(movieId, false)

        // then
        coVerify { repositoryMock.removeFavorite(movieId) }
        assertFalse((viewModel.uiState.value as MovieDetailsState.Success).isMovieFavorite)
    }
}