package com.example.movies

import com.example.movies.model.MoviesRepository
import com.example.movies.model.MoviesService
import com.example.movies.viewmodel.MoviesViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {
    viewModel{
        MoviesViewModel(get())
    }
}

val repositoryModule = module {
    single {
        MoviesRepository(get())
    }
}

val serviceModule = module {
    single {

        val client: OkHttpClient.Builder =
            OkHttpClient.Builder()
                .addInterceptor(
                    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                )

        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/movie/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .build().create(MoviesService::class.java)
    }
}