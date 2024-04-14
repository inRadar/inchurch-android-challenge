package com.thiagoperea.inchurchandroidchallenge.presentation.features.movielist

import com.thiagoperea.inchurchandroidchallenge.data.model.MovieListResponse
import com.thiagoperea.inchurchandroidchallenge.data.repository.MovieRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MovieListViewModelTest {

    private val testDispatcher = UnconfinedTestDispatcher()

    @MockK(relaxed = true)
    private lateinit var repositoryMock: MovieRepository

    lateinit var viewModel: MovieListViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        MockKAnnotations.init(this)
        viewModel = MovieListViewModel(repositoryMock)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test getMovieList success`() {
        // given
        val response = MovieListResponse(0, 0, emptyList())
        coEvery { repositoryMock.getMovieList() } returns Result.success(response)

        // when
        viewModel.getMovieList()

        // then
        assertTrue(viewModel.uiState.value is MovieListUiState.Success)
        assertEquals((viewModel.uiState.value as MovieListUiState.Success).data, response)
    }

    @Test
    fun `test getMovieList error`() {
        // given
        val error = Exception("error")
        coEvery { repositoryMock.getMovieList() } returns Result.failure(error)

        // when
        viewModel.getMovieList()

        // then
        assertTrue(viewModel.uiState.value is MovieListUiState.Error)
        assertEquals((viewModel.uiState.value as MovieListUiState.Error).message, error.message)
    }
}