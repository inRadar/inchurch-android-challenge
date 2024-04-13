package com.thiagoperea.inchurchandroidchallenge.presentation.features.moviedetails.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thiagoperea.inchurchandroidchallenge.data.model.AuthorDetailsResponse
import com.thiagoperea.inchurchandroidchallenge.data.model.ReviewResponse
import com.thiagoperea.inchurchandroidchallenge.presentation.theme.AppColors
import com.thiagoperea.inchurchandroidchallenge.presentation.theme.AppTextStyle
import com.thiagoperea.inchurchandroidchallenge.presentation.theme.InChurchAndroidChallengeTheme

@Composable
fun ReviewItem(reviewItem: ReviewResponse) {
    Row {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(AppColors.SelectedTabColor)
                .border(1.dp, AppColors.Accent, CircleShape),
            contentAlignment = Alignment.Center,
        ) {

            Text(
                text = reviewItem.authorDetails.rating.toString(),
                textAlign = TextAlign.Center,
                style = AppTextStyle.Medium12,
            )
        }

        Column(
            modifier = Modifier.padding(start = 12.dp),
        ) {
            Text(
                text = reviewItem.authorDetails.name,
                style = AppTextStyle.Medium12
            )

            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = reviewItem.content,
                style = AppTextStyle.Regular12
            )

        }
    }
}

@Preview
@Composable
private fun PreviewReviewItem() {
    InChurchAndroidChallengeTheme {
        Surface {
            ReviewItem(
                reviewItem = ReviewResponse(
                    authorDetails = AuthorDetailsResponse(
                        name = "John Doe",
                        rating = 8.0
                    ),
                    content = "This is a review"
                )
            )
        }
    }
}