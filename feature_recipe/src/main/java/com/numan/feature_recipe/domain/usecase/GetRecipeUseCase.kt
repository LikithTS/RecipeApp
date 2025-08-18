package com.numan.feature_recipe.domain.usecase

import com.numan.feature_recipe.domain.model.RecipeUiModel
import com.numan.feature_recipe.domain.repository.RecipesRepository
import com.numan.feature_recipe.domain.util.Result
import com.numan.feature_recipe.domain.util.APIResponse

/**
 * Created a empty use case. Since we're follow TDD.
 * We can avoid this use case. Since we're not doing anything here.
 * To keep our approach similar and also keeping scalability in mind.
 * I am going head with use case. We can use this use to modify the data later.
 */
class GetRecipeUseCase(
    private val recipesRepository: RecipesRepository
) {

    suspend operator fun invoke(limit: Int, skip: Int): Result<List<RecipeUiModel>, APIResponse> {
        return recipesRepository.getRecipes(limit = limit, skip = skip)
    }
}