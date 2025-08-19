package com.numan.feature_detail_recipe.data.util

import com.numan.feature_detail_recipe.data.model.Recipe
import com.numan.feature_detail_recipe.domain.model.DetailRecipeUiModel

/**
 * Mapping function from response to UI model.
 */
fun Recipe.toDetailRecipeUiModel(): DetailRecipeUiModel {
    return DetailRecipeUiModel(
        caloriesPerServing = this.caloriesPerServing,
        cookTimeMinutes = this.cookTimeMinutes,
        cuisine = this.cuisine,
        difficulty = this.difficulty,
        id = this.id,
        image = this.image,
        ingredients = this.ingredients,
        instructions = this.instructions,
        mealType = this.mealType,
        name = this.name,
        prepTimeMinutes = this.prepTimeMinutes,
        rating = this.rating,
        reviewCount = this.reviewCount,
        servings = this.servings
    )
}