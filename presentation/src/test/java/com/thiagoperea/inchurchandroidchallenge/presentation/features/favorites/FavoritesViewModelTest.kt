package com.thiagoperea.inchurchandroidchallenge.presentation.features.favorites

import com.thiagoperea.inchurchandroidchallenge.data.datasource.local.entity.MovieFavoriteEntity
import com.thiagoperea.inchurchandroidchallenge.data.repository.MovieRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class FavoritesViewModelTest {

    private val testDispatcher = UnconfinedTestDispatcher()

    @MockK(relaxed = true)
    private lateinit var repositoryMock: MovieRepository

    lateinit var viewModel: FavoritesViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        MockKAnnotations.init(this)
        viewModel = FavoritesViewModel(repositoryMock)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test loadFavorites success`() {
        // given
        val response = listOf(MovieFavoriteEntity(0L, "title", "poster", "overview", listOf(), 0.0, 0))
        coEvery { repositoryMock.getFavoriteMovies() } returns Result.success(response)

        // when
        viewModel.loadFavorites()

        // then
        assertTrue(viewModel.uiState.value is FavoritesUiState.Success)
        assertEquals((viewModel.uiState.value as FavoritesUiState.Success).data, response)
    }

    @Test
    fun `test loadFavorites empty`() {
        // given
        coEvery { repositoryMock.getFavoriteMovies() } returns Result.success(emptyList())

        // when
        viewModel.loadFavorites()

        // then
        assertTrue(viewModel.uiState.value is FavoritesUiState.Error)
    }

    @Test
    fun `test loadFavorites error`() {
        // given
        coEvery { repositoryMock.getFavoriteMovies() } returns Result.failure(Exception())

        // when
        viewModel.loadFavorites()

        // then
        assertTrue(viewModel.uiState.value is FavoritesUiState.Error)
    }

    @Test
    fun `test searchFavorites empty query`() {
        // given
        val response = listOf(MovieFavoriteEntity(0L, "title", "poster", "overview", listOf(), 0.0, 0))
        coEvery { repositoryMock.getFavoriteMovies() } returns Result.success(response)

        // when
        viewModel.loadFavorites()
        viewModel.searchFavorites("")

        // then
        assertTrue(viewModel.uiState.value is FavoritesUiState.Success)
        assertEquals((viewModel.uiState.value as FavoritesUiState.Success).data, response)
    }

    @Test
    fun `test searchFavorites success`() {
        // given
        val response = listOf(MovieFavoriteEntity(0L, "title", "poster", "overview", listOf(), 0.0, 0))
        coEvery { repositoryMock.getFavoriteMovies() } returns Result.success(response)

        // when
        viewModel.loadFavorites()
        viewModel.searchFavorites("title")

        // then
        assertTrue(viewModel.uiState.value is FavoritesUiState.Success)
        assertEquals((viewModel.uiState.value as FavoritesUiState.Success).data, response)
    }
}