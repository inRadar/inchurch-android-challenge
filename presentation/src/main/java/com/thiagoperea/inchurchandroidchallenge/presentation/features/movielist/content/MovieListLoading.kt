package com.thiagoperea.inchurchandroidchallenge.presentation.features.movielist.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thiagoperea.inchurchandroidchallenge.presentation.components.shimmerBrush
import com.thiagoperea.inchurchandroidchallenge.presentation.theme.InChurchAndroidChallengeTheme

fun LazyGridScope.movieListLoading() {
    items(10) {
        Box(
            modifier = Modifier
                .height(240.dp)
                .width(145.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(brush = shimmerBrush())
        )
    }
}

@Preview
@Composable
fun MovieListLoadingPreview() {
    InChurchAndroidChallengeTheme {
        Surface {
            LazyVerticalGrid(
                modifier = Modifier.padding(horizontal = 24.dp),
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                movieListLoading()
            }
        }
    }
}