package com.thiagoperea.inchurchandroidchallenge.presentation.features.movielist.content

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.thiagoperea.inchurchandroidchallenge.data.asImageUrl
import com.thiagoperea.inchurchandroidchallenge.data.model.MovieListResponse
import com.thiagoperea.inchurchandroidchallenge.data.model.MovieResponse
import com.thiagoperea.inchurchandroidchallenge.presentation.navigation.AppRoutes
import com.thiagoperea.inchurchandroidchallenge.presentation.theme.InChurchAndroidChallengeTheme

fun LazyGridScope.movieListWithContent(
    data: MovieListResponse,
    appNavController: NavController?,
) {
    items(data.results) { movieData ->
        AsyncImage(
            modifier = Modifier
                .width(145.dp)
                .clip(RoundedCornerShape(16.dp))
                .clickable { appNavController?.navigate(AppRoutes.MovieDetails.getRouteWithParam(movieData.id)) },
            model = movieData.posterPath.asImageUrl(),
            contentDescription = null,
            clipToBounds = true,
            contentScale = ContentScale.FillBounds
        )
    }
}

@Preview
@Composable
private fun PreviewMovieListWithContent() {
    InChurchAndroidChallengeTheme {
        Surface {
            LazyVerticalGrid(
                modifier = Modifier.padding(horizontal = 24.dp),
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                movieListWithContent(
                    MovieListResponse(
                        page = 1,
                        results = listOf(
                            MovieResponse(id = 1, posterPath = "/poster1.jpg"),
                        ),
                        totalPages = 1
                    ),
                    appNavController = null
                )
            }
        }
    }
}