package com.thiagoperea.inchurchandroidchallenge.presentation.features.movielist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.thiagoperea.inchurchandroidchallenge.presentation.components.shimmerBrush
import com.thiagoperea.inchurchandroidchallenge.presentation.theme.AppTextStyle
import com.thiagoperea.inchurchandroidchallenge.presentation.theme.InChurchAndroidChallengeTheme

@Composable
fun MovieListScreen(
    navController: NavController
) {
    LazyVerticalGrid(
        modifier = Modifier.padding(horizontal = 24.dp),
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {

        item(
            span = { GridItemSpan(maxCurrentLineSpan) }
        ) {
            Text(
                modifier = Modifier.padding(top = 24.dp),
                text = "What do you want to watch?",
                style = AppTextStyle.SemiBold18,
            )
        }

        items(10) {
            Box(
                modifier = Modifier
                    .height(210.dp)
                    .width(144.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(brush = shimmerBrush())
            ) {

            }
        }

        item(
            span = { GridItemSpan(maxCurrentLineSpan) }
        ) {
            Spacer(
                modifier = Modifier.height(24.dp)
            )
        }
    }
}

@Preview
@Composable
fun LoadMoviesScreenPreview() {
    InChurchAndroidChallengeTheme {
        Surface {
            MovieListScreen(rememberNavController())
        }
    }
}