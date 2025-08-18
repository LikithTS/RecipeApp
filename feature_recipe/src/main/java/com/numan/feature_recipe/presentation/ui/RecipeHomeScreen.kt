package com.numan.feature_recipe.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.numan.feature_recipe.presentation.HomeScreenViewModel
import com.numan.feature_recipe.presentation.model.RecipeListUiState
import org.koin.androidx.compose.koinViewModel

/**
 * Home screen which shows list of recipe data.
 */
@Composable
fun RecipeHomeScreen(
    modifier: Modifier
) {
    val homeScreenViewModel: HomeScreenViewModel = koinViewModel()
    RecipeHomeListView(
        modifier = modifier,
        homeScreenViewModel = homeScreenViewModel
    )
}

@Composable
fun RecipeHomeListView(
    modifier: Modifier,
    homeScreenViewModel: HomeScreenViewModel
) {

    val recipeState by homeScreenViewModel.uiState.collectAsState()

    when (val dataResp = recipeState) {
        is RecipeListUiState.Ideal -> {
            //Do nothing
            //Since it is default value.
        }

        is RecipeListUiState.Loading -> {
            //Progress bar view. For now I am showing default progress bar. We can customise this to based on project.
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is RecipeListUiState.Success -> {
            //Main UI content
            RecipeView(
                recipeUiModelList = dataResp.recipeUiList,
                isAppending = dataResp.isAppending,
                modifier = modifier,
                homeScreenViewModel = homeScreenViewModel
            )
        }

        is RecipeListUiState.Failure -> {
            //Error handling view
            HandleErrorView(dataResp.message)
        }
    }
}
