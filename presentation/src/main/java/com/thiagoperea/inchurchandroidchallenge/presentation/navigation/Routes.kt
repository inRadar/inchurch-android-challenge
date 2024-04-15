package com.thiagoperea.inchurchandroidchallenge.presentation.navigation

import com.thiagoperea.inchurchandroidchallenge.presentation.R

// Home Routes
object HomeRoutes {

    fun getAll() = listOf(
        MovieList,
        Favorites
    )

    data object MovieList : AppScreen(
        route = "movie_list",
        label = R.string.menu_movies,
        unselectedIcon = R.drawable.ic_home,
        selectedIcon = R.drawable.ic_home_selected,
    )

    data object Favorites : AppScreen(
        route = "favorites",
        label = R.string.menu_favorites,
        unselectedIcon = R.drawable.ic_star,
        selectedIcon = R.drawable.ic_star_selected,
    )
}

// App Routes

object AppRoutes {

    data object Home : AppScreen(
        route = "home"
    )

    data object MovieDetails : AppScreen(
        route = "movie_details/{movieId}"
    ) {
        fun getRouteWithParam(movieId: Long) = "movie_details/$movieId"
    }
}