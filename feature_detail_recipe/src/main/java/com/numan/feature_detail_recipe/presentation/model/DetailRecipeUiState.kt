package com.numan.feature_detail_recipe.presentation.model

import com.numan.feature_detail_recipe.domain.model.DetailRecipeUiModel
import com.numan.feature_detail_recipe.domain.util.NetworkErrorApiResponse

/**
 * To handle different screen state
 */
sealed class DetailRecipeUiState {

    object Ideal: DetailRecipeUiState()

    object Loading: DetailRecipeUiState()

    data class Success(
        val detailRecipeModel: DetailRecipeUiModel
    ): DetailRecipeUiState()

    data class Failure(val message: NetworkErrorApiResponse): DetailRecipeUiState()

}