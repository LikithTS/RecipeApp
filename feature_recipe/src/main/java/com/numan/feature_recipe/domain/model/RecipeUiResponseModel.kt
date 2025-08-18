package com.numan.feature_recipe.domain.model

/**
 * Below data class contains recipe list and its total size.
 */
data class RecipeUiResponseModel(
    val recipeUiMode: List<RecipeUiModel>,
    val totalItem: Int
)