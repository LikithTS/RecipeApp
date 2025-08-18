package com.numan.feature_recipe.domain.usecase

import com.numan.feature_recipe.domain.model.RecipeUiModel
import com.numan.feature_recipe.domain.util.FilterType
import com.numan.feature_recipe.domain.util.RecipeDifficultyLevel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * This use case will be only used to sort the list based on types.
 * We should use this class for just sorting purpose.
 * Do not add multiple functionality to this. It should not break single responsibility principle.
 */
class RecipeSortingUseCase {

    suspend operator fun invoke(
        filterType: FilterType,
        recipeList: List<RecipeUiModel>
    ): List<RecipeUiModel> =
        withContext(Dispatchers.Default) {
            when (filterType) {
                FilterType.DIFFICULTY -> {
                    recipeList.sortedBy { recipe ->
                        RecipeDifficultyLevel.valueOf(recipe.difficulty.uppercase()).ordinal
                    }
                }

                FilterType.RATING -> {
                    //Here we have only descending option. So we need to reverse the list once we sort
                    recipeList.sortedByDescending { recipe ->
                        recipe.rating
                    }
                }

                FilterType.NAME -> {
                    recipeList.sortedBy { recipe ->
                        recipe.name.lowercase()
                    }
                }

                FilterType.RESET -> {
                    recipeList
                }
            }
        }
}