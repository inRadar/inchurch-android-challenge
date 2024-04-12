package com.gentalha.moviechallenge.data.mappers

import com.gentalha.moviechallenge.data.cache.entity.MovieEntity
import com.gentalha.moviechallenge.data.remote.model.MovieResponse

fun MovieResponse.toEntity() = MovieEntity(
    id = this.id,
    title = this.title,
    overview = this.overview,
    releaseDate = this.releaseDate,
    posterUrl = this.getPosterUrl()
)