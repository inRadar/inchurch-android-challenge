package com.thiagoperea.inchurchandroidchallenge.presentation.features.home.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.thiagoperea.inchurchandroidchallenge.presentation.getAllAppScreens
import com.thiagoperea.inchurchandroidchallenge.presentation.ui.theme.AppColors

@Composable
fun HomeMenu(navController: NavController) {
    NavigationBar(
        containerColor = Color.Transparent,
    ) {
        val currentBackStack by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStack?.destination

        getAllAppScreens().forEach { screen ->
            val isSelected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
            val icon = if (isSelected) screen.selectedIcon else screen.unselectedIcon

            NavigationBarItem(
                selected = isSelected,
                label = {
                    Text(stringResource(id = screen.label))
                },
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
                colors = NavigationBarItemDefaults.colors().copy(
                    selectedIconColor = AppColors.Accent,
                    unselectedIconColor = AppColors.Unselected,
                    selectedTextColor = AppColors.Accent,
                    unselectedTextColor = AppColors.Unselected,
                    selectedIndicatorColor = Color.Transparent,
                )
            )
        }
    }
}