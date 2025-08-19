package com.numan.feature_detail_recipe.domain.model

/**
 * UI model data which will be used to display in screen. We can enhance if required in future.
 */
data class DetailRecipeUiModel(
    val caloriesPerServing: Int,
    val cookTimeMinutes: Int,
    val cuisine: String,
    val difficulty: String,
    val id: Int,
    val image: String,
    val ingredients: List<String>,
    val instructions: List<String>,
    val mealType: List<String>,
    val name: String,
    val prepTimeMinutes: Int,
    val rating: Double,
    val reviewCount: Int,
    val servings: Int
)
