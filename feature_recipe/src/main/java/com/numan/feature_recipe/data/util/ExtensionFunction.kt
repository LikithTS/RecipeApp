package com.numan.feature_recipe.data.util

import com.numan.feature_recipe.data.model.Recipe
import com.numan.feature_recipe.domain.model.RecipeUiModel

fun Recipe.toRecipeUiModel(): RecipeUiModel {
    return RecipeUiModel(
        id = this.id,
        name = this.name,
        image = this.image,
        rating = this.rating,
        reviewCount = this.reviewCount,
        userId = this.userId,
        difficulty = this.difficulty,
        cuisine = this.cuisine,
        serving = this.servings
    )
}