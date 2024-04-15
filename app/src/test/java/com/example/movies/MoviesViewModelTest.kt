package com.example.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.movies.model.MoviesRepository
import com.example.movies.model.dtos.GenreDTO
import com.example.movies.model.dtos.MovieDTO
import com.example.movies.model.dtos.MoviesDTO
import com.example.movies.viewmodel.MoviesViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class MoviesViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    private val testScope = TestCoroutineScope(testDispatcher)

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    @Mock
    private lateinit var moviesObserver: Observer<MoviesDTO>

    @Mock
    private lateinit var favoriteMoviesObserver: Observer<List<MovieDTO>>

    private lateinit var viewModel: MoviesViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)

        viewModel = MoviesViewModel(moviesRepository, testScope)


        viewModel.movies.observeForever(moviesObserver)
        viewModel.favoriteMovies.observeForever(favoriteMoviesObserver)


        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun teardown() {
        viewModel.movies.removeObserver(moviesObserver)
        viewModel.favoriteMovies.removeObserver(favoriteMoviesObserver)

        Dispatchers.resetMain()
        testScope.cleanupTestCoroutines()
    }

    @Test
    fun testGetMovies() {
        testScope.runBlockingTest {
            val moviesDTO = MoviesDTO(listOf(MovieDTO(1, "Test Movie", "", listOf(GenreDTO(1, "")), "", "")))
            `when`(moviesRepository.getPopularMovies()).thenReturn(moviesDTO)

            viewModel.getMovies()
            testScheduler.apply { advanceTimeBy(1000); runCurrent() }

            verify(moviesObserver).onChanged(moviesDTO)
        }
    }

    @Test
    fun testGetFavoriteMovies() {
        testScope.runBlockingTest {
            val ids = setOf("1", "2", "3")
            val moviesList = listOf(MovieDTO(1, "Test Movie 1", "", listOf(), "", ""),
                MovieDTO(2, "Test Movie 2", "", listOf(), "", ""),
                MovieDTO(3, "Test Movie 3", "", listOf(), "", ""))
            `when`(moviesRepository.getMoviesById(ids)).thenReturn(moviesList)

            viewModel.getFavoriteMovies(ids)
            testScheduler.apply { advanceTimeBy(1000); runCurrent() }

            verify(favoriteMoviesObserver).onChanged(moviesList)
        }
    }
}
