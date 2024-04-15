package com.thiagoperea.inchurchandroidchallenge.presentation.features.favorites

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.thiagoperea.inchurchandroidchallenge.presentation.R
import com.thiagoperea.inchurchandroidchallenge.presentation.features.favorites.content.EmptyFavoriteList
import com.thiagoperea.inchurchandroidchallenge.presentation.features.favorites.content.FavoriteList
import com.thiagoperea.inchurchandroidchallenge.presentation.theme.AppTextStyle
import com.thiagoperea.inchurchandroidchallenge.presentation.theme.InChurchAndroidChallengeTheme
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    appNavController: NavController,
    viewModel: FavoritesViewModel = koinViewModel()
) {

    val searchQuery = remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.loadFavorites()
    }

    val uiState = viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.menu_favorites),
                    style = AppTextStyle.SemiBold16,
                )
            }
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            value = searchQuery.value,
            onValueChange = { newValue ->
                searchQuery.value = newValue
                viewModel.searchFavorites(newValue)
            },
            shape = RoundedCornerShape(16.dp),
            maxLines = 1,
            label = {
                Text(stringResource(R.string.search))
            },
            placeholder = {
                Text(stringResource(R.string.search))
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = null
                )
            },
            trailingIcon = {
                if (searchQuery.value.isNotEmpty()) {
                    IconButton(onClick = { searchQuery.value = "" }) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = null
                        )
                    }
                }
            }
        )

        if (uiState.value is FavoritesUiState.Success) {
            FavoriteList(
                favorites = (uiState.value as FavoritesUiState.Success).data,
                appNavController = appNavController
            )
        } else {
            EmptyFavoriteList()
        }
    }
}

@Preview
@Composable
fun FavoritesScreenPreview() {
    InChurchAndroidChallengeTheme {
        Surface {
            FavoritesScreen(rememberNavController())
        }
    }
}