package com.thiagoperea.inchurchandroidchallenge.presentation.features.moviedetails.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.thiagoperea.inchurchandroidchallenge.data.asImageUrl
import com.thiagoperea.inchurchandroidchallenge.presentation.components.shimmerBrush
import com.thiagoperea.inchurchandroidchallenge.presentation.theme.AppTextStyle
import com.thiagoperea.inchurchandroidchallenge.presentation.theme.InChurchAndroidChallengeTheme

@Composable
fun MovieDetailsHeader(
    backgroundUrl: String,
    posterUrl: String,
    title: String,
    voteAverage: Double,
    isLoading: Boolean,
) {
    Box {
        Column {
            MovieDetailsTopImage(
                imageUrl = backgroundUrl,
                voteAverage = voteAverage,
                isLoading = isLoading
            )

            Spacer(Modifier.padding(top = 60.dp))
        }

        Row(
            modifier = Modifier.align(Alignment.BottomStart),
            verticalAlignment = Alignment.Bottom
        ) {

            AsyncImage(
                modifier = Modifier
                    .padding(start = 28.dp)
                    .width(80.dp)
                    .height(120.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(shimmerBrush(isLoading)),
                model = posterUrl.asImageUrl(),
                contentDescription = null,
                clipToBounds = true,
                contentScale = ContentScale.FillWidth
            )

            Text(
                modifier = Modifier
                    .padding(top = 72.dp, start = 12.dp, end = 28.dp)
                    .fillMaxWidth()
                    .background(shimmerBrush(isLoading)),
                text = title,
                style = AppTextStyle.SemiBold18,
                minLines = 2,
            )
        }
    }

}

@Preview
@Composable
private fun MovieDetailsHeaderPreview() {
    InChurchAndroidChallengeTheme {
        Surface {
            MovieDetailsHeader(
                backgroundUrl = "",
                posterUrl = "",
                title = "Movie Title",
                voteAverage = 7.5,
                isLoading = true
            )
        }
    }
}