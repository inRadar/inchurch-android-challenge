package com.thiagoperea.inchurchandroidchallenge.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

sealed class AppScreen(
    val route: String,
    @StringRes val label: Int = 0,
    @DrawableRes val unselectedIcon: Int = 0,
    @DrawableRes val selectedIcon: Int = 0,
)