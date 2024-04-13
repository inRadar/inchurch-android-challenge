package com.thiagoperea.inchurchandroidchallenge.data

fun String?.asImageUrl(): String {
    return "https://image.tmdb.org/t/p/w500$this"
}

fun String?.asOriginalImageUrl(): String {
    return "https://image.tmdb.org/t/p/original$this"
}