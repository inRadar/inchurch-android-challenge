package com.thiagoperea.inchurchandroidchallenge.presentation.features.moviedetails.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.thiagoperea.inchurchandroidchallenge.data.asImageUrl
import com.thiagoperea.inchurchandroidchallenge.data.asOriginalImageUrl
import com.thiagoperea.inchurchandroidchallenge.presentation.components.shimmerBrush
import com.thiagoperea.inchurchandroidchallenge.presentation.theme.AppColors
import com.thiagoperea.inchurchandroidchallenge.presentation.theme.AppTextStyle
import com.thiagoperea.inchurchandroidchallenge.presentation.theme.InChurchAndroidChallengeTheme

@Composable
fun MovieDetailsTopImage(
    imageUrl: String,
    voteAverage: Double,
    isLoading: Boolean
) {
    val contentColor = if (isLoading) {
        Color.Transparent
    } else {
        AppColors.Orange
    }

    Box {
        AsyncImage(
            modifier = Modifier
                .height(210.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
                .background(shimmerBrush(isLoading)),
            model = imageUrl.asOriginalImageUrl(),
            contentDescription = null,
            clipToBounds = true,
            contentScale = ContentScale.FillBounds
        )

        Row(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(12.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(shimmerBrush(isLoading, Color.Black.copy(alpha = 0.5f)))
                .padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(16.dp),
                imageVector = Icons.Outlined.Star,
                contentDescription = null,
                tint = contentColor
            )

            Spacer(modifier = Modifier.size(4.dp))

            Text(
                text = String.format("%.1f", voteAverage),
                color = contentColor,
                style = AppTextStyle.SemiBold12
            )
        }
    }
}

@Preview
@Composable
private fun MovieDetailsTopImagePreview() {
    InChurchAndroidChallengeTheme {
        Surface {
            MovieDetailsTopImage("", 8.4238, false)
        }
    }
}