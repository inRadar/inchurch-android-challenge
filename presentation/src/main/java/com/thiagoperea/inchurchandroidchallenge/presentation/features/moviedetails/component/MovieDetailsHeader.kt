package com.thiagoperea.inchurchandroidchallenge.presentation.features.moviedetails.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thiagoperea.inchurchandroidchallenge.presentation.theme.AppTextStyle
import com.thiagoperea.inchurchandroidchallenge.presentation.theme.InChurchAndroidChallengeTheme

@Composable
fun MovieDetailsHeader(
    backgroundUrl: String,
    posterUrl: String,
    title: String,
    voteAverage: Double,
) {
    Box {
        Column {
            MovieDetailsTopImage(
                imageUrl = backgroundUrl,
                voteAverage = voteAverage
            )

            Spacer(Modifier.padding(top = 60.dp))
        }

        Row(
            modifier = Modifier.align(Alignment.BottomStart),
            verticalAlignment = Alignment.Bottom
        ) {

            Box(
                modifier = Modifier
                    .padding(start = 28.dp)
                    .width(95.dp)
                    .height(120.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.Blue.copy(alpha = 0.5f))
            )

            Text(
                modifier = Modifier.padding(top = 72.dp, start = 12.dp, end = 28.dp),
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
                voteAverage = 7.5
            )
        }
    }
}