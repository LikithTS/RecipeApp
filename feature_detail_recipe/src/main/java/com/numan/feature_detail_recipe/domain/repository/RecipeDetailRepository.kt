package com.numan.feature_detail_recipe.domain.repository

import com.numan.feature_detail_recipe.domain.model.DetailRecipeUiModel
import com.numan.feature_detail_recipe.domain.util.NetworkErrorApiResponse
import com.numan.feature_detail_recipe.domain.util.Result

/**
 * Repository to get data and return the processed result.
 */
interface RecipeDetailRepository {

    suspend fun getDetailRecipeBasedOnId(id: Int): Result<DetailRecipeUiModel, NetworkErrorApiResponse>
}