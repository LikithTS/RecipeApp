package com.numan.feature_recipe.domain.repository

import com.numan.feature_recipe.domain.model.RecipeUiModel
import com.numan.feature_recipe.domain.util.APIResponse
import com.numan.feature_recipe.domain.util.Result

interface RecipesRepository {

    /*
        Just declare method signature here.
        Method will return recipes based on page number and count.
     */
    suspend fun getRecipes(limit: Int, skip: Int): Result<List<RecipeUiModel>, APIResponse>

}