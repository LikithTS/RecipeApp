package com.numan.feature_detail_recipe.data.remote

import com.numan.feature_detail_recipe.data.model.Recipe
import com.numan.feature_detail_recipe.data.util.NetworkConstants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RecipeDetailAPIInterface {

    /**
     * End point API to get detail screen data
     */
    @GET(NetworkConstants.SINGLE_RECIPE_END_POINT)
    suspend fun getRecipeDetailData(
        @Path("id") id: Int,
    ): Response<Recipe>
}