package com.numan.feature_recipe.presentation.ui

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.numan.feature_recipe.domain.model.RecipeUiModel
import com.numan.feature_recipe.domain.util.DeviceConfiguration

/**
 * Below view will handle based on window size and show different views.
 */
@Composable
fun RecipeView(recipeUiModelList: List<RecipeUiModel>, modifier: Modifier) {

    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    val deviceConfiguration = DeviceConfiguration.fromWindowSizeClass(windowSizeClass)

    when (deviceConfiguration) {
        DeviceConfiguration.MOBILE_PORTRAIT, DeviceConfiguration.TABLET_PORTRAIT ->  {
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .windowInsetsPadding(WindowInsets.displayCutout)
            ) {
                items(recipeUiModelList) { recipeData ->
                    RecipePortraitCardView(recipeData)
                }
            }
        }

        DeviceConfiguration.MOBILE_LANDSCAPE, DeviceConfiguration.TABLET_LANDSCAPE, DeviceConfiguration.DESKTOP -> {
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .windowInsetsPadding(WindowInsets.displayCutout)
            ) {
                items(recipeUiModelList) { recipeData ->
                    RecipeLandscapeCardView(recipeData)
                }
            }
        }
    }
}