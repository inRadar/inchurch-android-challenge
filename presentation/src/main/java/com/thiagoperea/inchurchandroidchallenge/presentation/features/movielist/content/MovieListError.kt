package com.thiagoperea.inchurchandroidchallenge.presentation.features.movielist.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thiagoperea.inchurchandroidchallenge.presentation.theme.AppColors
import com.thiagoperea.inchurchandroidchallenge.presentation.theme.AppTextStyle
import com.thiagoperea.inchurchandroidchallenge.presentation.theme.InChurchAndroidChallengeTheme

fun LazyGridScope.movieListError(
    onTryAgainClick: () -> Unit
) {
    item(
        span = { GridItemSpan(maxLineSpan) }
    ) {
        Text(
            text = "There was an error loading the movies :(",
            style = AppTextStyle.SemiBold16,
            textAlign = TextAlign.Center
        )
    }

    item(
        span = { GridItemSpan(maxLineSpan) }
    ) {
        Text(
            text = "Please try again clicking the button below.",
            color = AppColors.Gray,
            style = AppTextStyle.Medium12,
            textAlign = TextAlign.Center
        )
    }

    item(
        span = { GridItemSpan(maxLineSpan) }
    ) {
        ElevatedButton(
            onClick = { onTryAgainClick() },
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = AppColors.Background,
                contentColor = AppColors.onBackground,
            ),
        ) {
            Text("Try again")
        }
    }
}


@Preview
@Composable
fun MovieListErrorPreview() {
    InChurchAndroidChallengeTheme {
        Surface {
            LazyVerticalGrid(
                modifier = Modifier.padding(horizontal = 24.dp),
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                movieListError {}
            }
        }
    }
}