package com.gentalha.moviechallenge.data.remote.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.gentalha.moviechallenge.data.cache.MovieDatabase
import com.gentalha.moviechallenge.data.cache.entity.MovieEntity
import com.gentalha.moviechallenge.data.mappers.toEntity
import com.gentalha.moviechallenge.data.remote.service.MovieService
import retrofit2.HttpException
import java.io.IOException

const val ONE = 1

@OptIn(ExperimentalPagingApi::class)
class MovieRemoteMediator(
    private val movieDb: MovieDatabase,
    private val movieService: MovieService
) : RemoteMediator<Int, MovieEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieEntity>
    ): MediatorResult {


        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> ONE
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )

                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        ONE
                    } else {
                        (lastItem.id / state.config.pageSize) + ONE
                    }
                }
            }

            println("THG_page = $loadKey")

            val movies = movieService.getMovies(
                page = loadKey
            ).movies

            movieDb.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    movieDb.movieDao.clearAll()
                }
                val movieEntities = movies.map { it.toEntity() }
                movieDb.movieDao.upsertAll(movieEntities)
            }

            MediatorResult.Success(
                endOfPaginationReached = movies.isEmpty()
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        } catch (e: IllegalArgumentException) {
            MediatorResult.Error(e)
        }
    }
}