package com.thiagoperea.inchurchandroidchallenge.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object KoinModules {

    fun getModules() = listOf(
        presentationModule,
        domainModule,
        repositoryModule,
        datasourceModule
    )

    private val presentationModule = module {
//        viewModel { HomeViewModel(get()) }
    }

    private val domainModule = module {
//        factory { GetMoviesUseCase(get()) }
    }

    private val repositoryModule = module {
//        single<MovieRepository> { MovieRepositoryImpl(get()) }
    }

    private val datasourceModule = module {
//        single<MovieDataSource> { MovieDataSourceImpl(get()) }
    }
}