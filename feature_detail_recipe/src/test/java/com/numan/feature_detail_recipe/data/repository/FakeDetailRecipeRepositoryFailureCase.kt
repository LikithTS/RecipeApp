package com.numan.feature_detail_recipe.data.repository

import com.numan.feature_detail_recipe.domain.model.DetailRecipeUiModel
import com.numan.feature_detail_recipe.domain.repository.RecipeDetailRepository
import com.numan.feature_detail_recipe.domain.util.NetworkErrorApiResponse
import com.numan.feature_detail_recipe.domain.util.Result

class FakeDetailRecipeRepositoryFailureCase : RecipeDetailRepository{

    override suspend fun getDetailRecipeBasedOnId(id: Int): Result<DetailRecipeUiModel, NetworkErrorApiResponse> {
        return Result.Error(NetworkErrorApiResponse.BAD_REQUEST)
    }
}