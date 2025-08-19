package com.numan.feature_recipe.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.numan.feature_recipe.R
import com.numan.feature_recipe.presentation.HomeScreenViewModel
import com.numan.feature_recipe.presentation.model.RecipeListUiState
import org.koin.androidx.compose.koinViewModel

/**
 * Home screen which shows list of recipe data.
 * Fixed few minor UI issues
 */
@Composable
fun RecipeHomeScreen(
    modifier: Modifier,
    onCardItemClicked: (Int) -> Unit
) {
    val homeScreenViewModel: HomeScreenViewModel = koinViewModel()
    RecipeHomeListView(
        modifier = modifier,
        homeScreenViewModel = homeScreenViewModel,
        onCardItemClicked = onCardItemClicked
    )
}

@Composable
fun RecipeHomeListView(
    modifier: Modifier,
    homeScreenViewModel: HomeScreenViewModel,
    onCardItemClicked: (Int) -> Unit
) {

    val recipeState by homeScreenViewModel.uiState.collectAsStateWithLifecycle()
    val selectedFilter by homeScreenViewModel.selectedFilter.collectAsStateWithLifecycle()


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
            Box(modifier = modifier.fillMaxSize()) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(R.string.recipes),
                        textAlign = TextAlign.Center,
                        color = colorResource(R.color.white),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W500,
                        modifier = Modifier.padding(top = 10.dp)
                            .fillMaxWidth()
                            .background(color = colorResource(R.color.primary_blue))
                            .padding(all = 10.dp)
                    )
                    RecipeView(
                        recipeUiModelList = dataResp.recipeUiList,
                        isAppending = dataResp.isAppending,
                        modifier = Modifier,
                        homeScreenViewModel = homeScreenViewModel,
                        onCardClicked = onCardItemClicked
                    )

                }

                FabWithMenuView(
                    selected = selectedFilter,
                    onSelected = { type -> homeScreenViewModel.sortRecipes(type) },
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(16.dp)
                )

            }
        }

        is RecipeListUiState.Failure -> {
            //Error handling view
            HandleErrorView(dataResp.message)
        }
    }

}
