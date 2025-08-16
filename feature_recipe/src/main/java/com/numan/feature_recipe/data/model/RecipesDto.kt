package com.numan.feature_recipe.data.model

data class RecipesDto(
    val limit: Int,
    val recipes: List<Recipe>,
    val skip: Int,
    val total: Int
)