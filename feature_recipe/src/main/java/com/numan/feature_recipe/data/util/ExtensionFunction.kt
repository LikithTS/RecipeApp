package com.numan.feature_recipe.data.util

import com.numan.feature_recipe.data.model.RecipesDto
import com.numan.feature_recipe.domain.model.RecipeUiModel
import com.numan.feature_recipe.domain.model.RecipeUiResponseModel

/**
 * I need to alter this extension function to have total.
 */
fun RecipesDto.toRecipeUiModel(): RecipeUiResponseModel {
    return RecipeUiResponseModel(
        recipeUiMode = recipes.map { recipe ->
            RecipeUiModel(
                id = recipe.id,
                name = recipe.name,
                image = recipe.image,
                rating = recipe.rating,
                reviewCount = recipe.reviewCount,
                userId = recipe.userId,
                difficulty = recipe.difficulty,
                cuisine = recipe.cuisine,
                serving = recipe.servings
            )
        },
    totalItem = total
    )
}