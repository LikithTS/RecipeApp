package com.numan.feature_detail_recipe.data.repository

import com.numan.feature_detail_recipe.domain.model.DetailRecipeUiModel
import com.numan.feature_detail_recipe.domain.repository.RecipeDetailRepository
import com.numan.feature_detail_recipe.domain.util.NetworkErrorApiResponse
import com.numan.feature_detail_recipe.domain.util.Result

class FakeDetailRecipeRepositoryFailureCase3 : RecipeDetailRepository{

    override suspend fun getDetailRecipeBasedOnId(id: Int): Result<DetailRecipeUiModel, NetworkErrorApiResponse> {
        return Result.Error(NetworkErrorApiResponse.NOT_FOUND)
    }
}