package com.gentalha.moviechallenge.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gentalha.moviechallenge.ui.theme.BlueLight

@Composable
fun CircularLoading(modifier: Modifier = Modifier) {
    CircularProgressIndicator(
        modifier = modifier
            .size(32.dp),
        color = BlueLight
    )
}