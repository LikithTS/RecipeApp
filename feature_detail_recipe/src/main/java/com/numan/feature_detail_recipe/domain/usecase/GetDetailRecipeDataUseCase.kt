package com.numan.feature_detail_recipe.domain.usecase

import com.numan.feature_detail_recipe.domain.model.DetailRecipeUiModel
import com.numan.feature_detail_recipe.domain.repository.RecipeDetailRepository
import com.numan.feature_detail_recipe.domain.util.NetworkErrorApiResponse
import com.numan.feature_detail_recipe.domain.util.Result

/**
 * Use case here is not having a much benefit atm, but it can be handy if any single responsibility functionality to be handled here.
 */
class GetDetailRecipeDataUseCase(
    private val recipeDetailRepository: RecipeDetailRepository
) {

    suspend operator fun invoke(id: Int): Result<DetailRecipeUiModel, NetworkErrorApiResponse> {
        return recipeDetailRepository.getDetailRecipeBasedOnId(id)
    }
}