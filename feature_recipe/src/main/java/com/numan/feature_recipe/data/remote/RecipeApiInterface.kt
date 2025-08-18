package com.numan.feature_recipe.data.remote

import com.numan.feature_recipe.data.model.RecipesDto
import com.numan.feature_recipe.data.util.NetworkConstants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * API calls
 */
interface RecipeApiInterface {

    @GET(NetworkConstants.RECIPE_END_POINT)
    suspend fun getRecipesData(
        @Query("limit") limit: Int,
        @Query("skip") skip: Int
    ): Response<RecipesDto>


}