package com.numan.feature_detail_recipe.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.numan.feature_detail_recipe.presentation.DetailRecipeViewModel
import com.numan.feature_detail_recipe.presentation.model.DetailRecipeUiState
import org.koin.androidx.compose.koinViewModel

/**
 * View to handle the state
 */
@Composable
fun DetailRecipeScreenView(
    modifier: Modifier,
    recipeId: Int,
    onButtonClick: () -> Unit
) {

    val detailRecipeViewModel = koinViewModel<DetailRecipeViewModel>()
    val detailRecipeUiState by detailRecipeViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        detailRecipeViewModel.getDetailRecipeDataById(recipeId)
    }

    when (val dataResp = detailRecipeUiState) {
        is DetailRecipeUiState.Ideal -> {
            //Do nothing
            //Since it is default value.
        }

        is DetailRecipeUiState.Loading -> {
            //Progress bar view. For now I am showing default progress bar. We can customise this to based on project.
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is DetailRecipeUiState.Success -> {
            //Main UI content
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .windowInsetsPadding(WindowInsets.displayCutout)
            ) {
                DetailRecipeHeaderView(
                    onBackButtonClicked = onButtonClick,
                    recipeName = dataResp.detailRecipeModel.name
                )
                HorizontalDivider()
                DetailRecipeContentView(recipeData = dataResp.detailRecipeModel)
            }
        }

        is DetailRecipeUiState.Failure -> {
            //Error handling view
            HandleErrorView(dataResp.message)
        }
    }
}