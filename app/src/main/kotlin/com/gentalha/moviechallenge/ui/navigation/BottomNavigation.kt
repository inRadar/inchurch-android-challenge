package com.gentalha.moviechallenge.ui.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import com.gentalha.moviechallenge.ui.theme.BlueDark
import com.gentalha.moviechallenge.ui.theme.BlueLight

@Composable
fun RowScope.BottomNavigationItem(
    tab: Tab
) {
    val tabNavigator = LocalTabNavigator.current

    NavigationBarItem(
        selected = tabNavigator.current == tab,
        onClick = { tabNavigator.current = tab },
        icon = {
            Icon(
                painter = tab.options.icon!!,
                contentDescription = tab.options.title
            )
        },
        colors = NavigationBarItemDefaults.colors(
            indicatorColor = BlueDark,
            selectedIconColor = BlueLight,
            unselectedIconColor = Color.LightGray
        )
    )

}