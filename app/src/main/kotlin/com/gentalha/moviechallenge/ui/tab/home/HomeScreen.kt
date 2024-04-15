package com.gentalha.moviechallenge.ui.tab.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.gentalha.moviechallenge.ui.navigation.BottomNavigationItem
import com.gentalha.moviechallenge.ui.screens.MovieDetailScreen
import com.gentalha.moviechallenge.ui.tab.FavoriteTab
import com.gentalha.moviechallenge.ui.tab.MoviesTab
import com.gentalha.moviechallenge.ui.theme.BlueDark
import com.gentalha.moviechallenge.ui.theme.BlueGrey

class HomeScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val home = MoviesTab { navigator.push(MovieDetailScreen(it)) }
        TabNavigator(tab = home) {
            Scaffold(
                bottomBar = {
                    BottomAppBar(
                        modifier = Modifier
                            .background(BlueDark)
                            .clip(
                                RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                            )
                            .height(60.dp),
                        containerColor = BlueGrey
                    ) {
                        BottomNavigationItem(tab = home)
                        BottomNavigationItem(tab = FavoriteTab)
                    }
                }
            ) {
                Box(
                    Modifier.padding(it)
                ) {
                    CurrentTab()
                }
            }
        }
    }
}