package com.numan.feature_recipe.data.repository

import com.numan.feature_recipe.domain.model.RecipeUiResponseModel
import com.numan.feature_recipe.domain.repository.RecipesRepository
import com.numan.feature_recipe.domain.util.NetworkErrorApiResponse
import com.numan.feature_recipe.domain.util.Result

/**
 * I need to update parameter names to support dynamic pagination.
 * Previous commit had hardcoded scenario to test API working.
 */
class FakeRecipesRepositoryFailureCase3 : RecipesRepository {

    override suspend fun getRecipes(
        page: Int,
        pageCount: Int
    ): Result<RecipeUiResponseModel, NetworkErrorApiResponse> {
        return Result.Error(NetworkErrorApiResponse.EMPTY_RESPONSE)
    }
}