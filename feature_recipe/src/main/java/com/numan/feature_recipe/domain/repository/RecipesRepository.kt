package com.numan.feature_recipe.domain.repository

import com.numan.feature_recipe.domain.model.RecipeUiResponseModel
import com.numan.feature_recipe.domain.util.NetworkErrorApiResponse
import com.numan.feature_recipe.domain.util.Result

interface RecipesRepository {

    /*
        Just declare method signature here.
        Method will return recipes based on page number and count.
     */
    suspend fun getRecipes(page: Int, pageCount: Int): Result<RecipeUiResponseModel, NetworkErrorApiResponse>

}