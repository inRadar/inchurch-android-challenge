package com.thiagoperea.inchurchandroidchallenge.presentation

import com.thiagoperea.inchurchandroidchallenge.data.datasource.local.dao.MovieFavoriteDao
import com.thiagoperea.inchurchandroidchallenge.data.datasource.remote.TMDBApi
import com.thiagoperea.inchurchandroidchallenge.data.repository.MovieRepository
import com.thiagoperea.inchurchandroidchallenge.presentation.features.favorites.FavoritesViewModel
import com.thiagoperea.inchurchandroidchallenge.presentation.features.moviedetails.MovieDetailsViewModel
import com.thiagoperea.inchurchandroidchallenge.presentation.features.movielist.MovieListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object TestKoinModules {

    fun getModules() = listOf(presentationModule, dataModule)

    private val presentationModule = module {
        viewModel { MovieListViewModel(get()) }
        viewModel { MovieDetailsViewModel(get()) }
        viewModel { FavoritesViewModel(get()) }
    }

    private val dataModule = module {
        single { MovieRepository(get(), get()) }

        // remote datasource
        single<TMDBApi> { FakeTMDBApi() }

        // local datasource
        single<MovieFavoriteDao> { FakeMovieFavoriteDao() }
    }
}