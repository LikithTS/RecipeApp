package com.numan.feature_recipe.domain.model

/**
 * We don't need all the information or data returned from the api. So we can map to what UI needs.
 * Here I am sticking to below data. We can extend it later if need.
 */

data class RecipeUiModel(
    val id: Int,
    val image: String,
    val name: String,
    val rating: Double,
    val reviewCount: Int,
    val userId: Int,
    val difficulty: String,
    val cuisine: String,
    val serving: Int
)
