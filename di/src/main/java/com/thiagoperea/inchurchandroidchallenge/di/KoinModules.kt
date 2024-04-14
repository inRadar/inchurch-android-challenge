package com.thiagoperea.inchurchandroidchallenge.di

import androidx.room.Room
import com.thiagoperea.inchurchandroidchallenge.data.datasource.local.InChurchDatabase
import com.thiagoperea.inchurchandroidchallenge.data.datasource.remote.TMDBApi
import com.thiagoperea.inchurchandroidchallenge.data.repository.MovieRepository
import com.thiagoperea.inchurchandroidchallenge.presentation.features.favorites.FavoritesViewModel
import com.thiagoperea.inchurchandroidchallenge.presentation.features.moviedetails.MovieDetailsViewModel
import com.thiagoperea.inchurchandroidchallenge.presentation.features.movielist.MovieListViewModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private fun apiKeyInterceptor(chain: Interceptor.Chain) = chain.proceed(
    chain.request()
        .newBuilder()
        .addHeader("Authorization", "Bearer ${BuildConfig.API_KEY_TMDB}")
        .build()
)

object KoinModules {

    fun getModules() = listOf(presentationModule, dataModule)

    private val presentationModule = module {
        viewModel { MovieListViewModel(get()) }
        viewModel { MovieDetailsViewModel(get()) }
        viewModel { FavoritesViewModel(get()) }
    }

    private val dataModule = module {
        single { MovieRepository(get(), get()) }

        // remote datasource

        single {
            val client = OkHttpClient.Builder()
                .addInterceptor { apiKeyInterceptor(it) }
                .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
                .build()

            Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.themoviedb.org/3/")
                .build()
                .create(TMDBApi::class.java)
        }

        // local datasource

        single {
            Room.databaseBuilder(get(), InChurchDatabase::class.java, "in_church_database")
                .fallbackToDestructiveMigration()
                .build()
        }

        factory { get<InChurchDatabase>().movieFavoriteDao() }
    }
}