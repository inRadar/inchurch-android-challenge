package com.gentalha.moviechallenge.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.gentalha.moviechallenge.data.cache.dao.MovieDao
import com.gentalha.moviechallenge.data.remote.model.MovieResponse
import com.gentalha.moviechallenge.data.remote.service.MovieService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MoviePagingSource @Inject constructor(
    private val service: MovieService,
    private val movieDao: MovieDao
) : PagingSource<Int, MovieResponse>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieResponse> {
        return try {
            val currentPage = params.key ?: 1
            val response = service.getMovies(
                page = currentPage
            )
            val movies = response.movies.map { movieResponse ->
                val favoriteMovie = movieDao.searchBy(movieResponse.title).firstOrNull()
                movieResponse.copy(isFavorite = favoriteMovie != null)
            }
            LoadResult.Page(
                data = movies,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (movies.isEmpty()) null else response.page!! + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieResponse>): Int? {
        return state.anchorPosition
    }
}