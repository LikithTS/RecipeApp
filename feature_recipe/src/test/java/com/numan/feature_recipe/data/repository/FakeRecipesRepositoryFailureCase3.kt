package com.numan.feature_recipe.data.repository

import com.numan.feature_recipe.domain.model.RecipeUiModel
import com.numan.feature_recipe.domain.repository.RecipesRepository
import com.numan.feature_recipe.domain.util.APIResponse
import com.numan.feature_recipe.domain.util.Result

class FakeRecipesRepositoryFailureCase3 : RecipesRepository {

    override suspend fun getRecipes(
        limit: Int,
        skip: Int
    ): Result<List<RecipeUiModel>, APIResponse> {
        return Result.Error(APIResponse.EMPTY_RESPONSE)
    }
}