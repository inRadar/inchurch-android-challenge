package com.gentalha.moviechallenge.data.mappers

import com.gentalha.moviechallenge.data.cache.entity.MovieEntity
import com.gentalha.moviechallenge.data.remote.model.MovieResponse
import com.gentalha.moviechallenge.ui.model.Movie

fun MovieEntity.toUi() = Movie(
    id = this.id,
    title = this.title,
    overview = this.overview,
    releaseDate = this.releaseDate,
    posterUrl = this.posterUrl,
    isFavorite = this.isFavorite
)

fun MovieResponse.toUi() = Movie(
    id = this.id,
    title = this.title,
    overview = this.overview,
    releaseDate = this.releaseDate,
    posterUrl = this.getPosterUrl(),
    isFavorite = this.isFavorite
)

fun Movie.toEntity() = MovieEntity(
    id = this.id,
    title = this.title,
    overview = this.overview,
    releaseDate = this.releaseDate,
    posterUrl = this.posterUrl,
    isFavorite = this.isFavorite
)