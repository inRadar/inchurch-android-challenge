package com.thiagoperea.inchurchandroidchallenge.presentation.features.moviedetails.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.BrushPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.thiagoperea.inchurchandroidchallenge.data.asImageUrl
import com.thiagoperea.inchurchandroidchallenge.data.model.CastResponse
import com.thiagoperea.inchurchandroidchallenge.presentation.components.shimmerBrush
import com.thiagoperea.inchurchandroidchallenge.presentation.theme.AppTextStyle
import com.thiagoperea.inchurchandroidchallenge.presentation.theme.InChurchAndroidChallengeTheme

@Composable
fun CastItem(castItem: CastResponse) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .size(100.dp)
                .clip(CircleShape),
            model = castItem.profilePath.asImageUrl(),
            contentDescription = castItem.name,
            clipToBounds = true,
            placeholder = BrushPainter(brush = shimmerBrush(true)),
            contentScale = ContentScale.FillWidth,
        )

        Text(
            text = castItem.name,
            style = AppTextStyle.Medium12,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Preview
@Composable
private fun PreviewReviewItem() {
    InChurchAndroidChallengeTheme {
        Surface {
            CastItem(
                castItem = CastResponse(
                    name = "Name",
                    profilePath = "/profile.jpg",
                    id = 123
                )
            )
        }
    }
}