package com.numan.feature_recipe.presentation.model

import com.numan.feature_recipe.domain.model.RecipeUiModel
import com.numan.feature_recipe.domain.util.APIResponse

/**
 * Sealed class to maintain UI state in the screen
 */
sealed class RecipeListUiState {

    object Ideal: RecipeListUiState()

    object Loading: RecipeListUiState()

    data class Success(
        val recipeUiList: List<RecipeUiModel>
    ): RecipeListUiState()

    data class Error(val message: APIResponse): RecipeListUiState()
}