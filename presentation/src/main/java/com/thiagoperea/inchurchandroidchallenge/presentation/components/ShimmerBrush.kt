package com.thiagoperea.inchurchandroidchallenge.presentation.components

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun shimmerBrush(): Brush {
    val shimmerColors = listOf(
        Color.LightGray.copy(alpha = 0.1F),
        Color.LightGray.copy(alpha = 0.5F),
        Color.LightGray.copy(alpha = 0.1F),
    )

    val transition = rememberInfiniteTransition()
    val animation = transition.animateFloat(
        label = "shimmerLabel",
        initialValue = 0F,
        targetValue = 2000F,
        animationSpec = infiniteRepeatable(
            animation = tween(2000),
            repeatMode = RepeatMode.Reverse
        )
    )

    return Brush.linearGradient(
        colors = shimmerColors,
        start = Offset(0F, animation.value),
        end = Offset(animation.value, animation.value)
    )
}