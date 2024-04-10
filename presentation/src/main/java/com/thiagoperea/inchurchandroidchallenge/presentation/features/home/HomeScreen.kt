package com.thiagoperea.inchurchandroidchallenge.presentation.features.home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.thiagoperea.inchurchandroidchallenge.presentation.AppScreen
import com.thiagoperea.inchurchandroidchallenge.presentation.features.favorites.FavoritesScreen
import com.thiagoperea.inchurchandroidchallenge.presentation.features.movielist.MovieListScreen
import com.thiagoperea.inchurchandroidchallenge.presentation.getAllAppScreens
import com.thiagoperea.inchurchandroidchallenge.presentation.ui.theme.InChurchAndroidChallengeTheme

data class HomeMenuItem(
    val label: String,
    @DrawableRes val iconUnselected: Int,
    @DrawableRes val iconSelected: Int,
)

@Composable
fun HomeScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar {
                val currentBackStack by navController.currentBackStackEntryAsState()
                val currentDestination = currentBackStack?.destination

                getAllAppScreens().forEach { screen ->
                    val isSelected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
                    val icon = if (isSelected) screen.selectedIcon else screen.unselectedIcon

                    NavigationBarItem(
                        selected = isSelected,
                        label = { Text(stringResource(id = screen.label)) },
                        icon = {
                            Icon(
                                contentDescription = screen.route,
                                painter = painterResource(icon),
                            )
                        },
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                    )
                }
            }
        }
    ) { safePadding ->
        NavHost(
            modifier = Modifier.padding(safePadding),
            navController = navController,
            startDestination = AppScreen.MovieList.route,
        ) {
            composable(AppScreen.MovieList.route) { MovieListScreen(navController) }
            composable(AppScreen.Favorites.route) { FavoritesScreen(navController) }
        }
    }
}

@Preview
@Composable
fun previewHomeScreen() {
    InChurchAndroidChallengeTheme {
        Surface {
            HomeScreen()
        }
    }
}