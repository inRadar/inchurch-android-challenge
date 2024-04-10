package com.thiagoperea.inchurchandroidchallenge.presentation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

fun getAllAppScreens() = listOf(
    AppScreen.MovieList,
    AppScreen.Favorites
)

sealed class AppScreen(
    val route: String,
    @StringRes val label: Int,
    @DrawableRes val unselectedIcon: Int,
    @DrawableRes val selectedIcon: Int,
) {

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