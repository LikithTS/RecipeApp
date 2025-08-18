package com.numan.feature_recipe.data.repository

import com.google.gson.Gson
import com.numan.feature_recipe.domain.model.RecipeUiResponseModel
import com.numan.feature_recipe.domain.repository.RecipesRepository
import com.numan.feature_recipe.domain.util.NetworkErrorApiResponse
import com.numan.feature_recipe.domain.util.JsonFileReader
import com.numan.feature_recipe.domain.util.Result

/**
 * I need to update parameter names to support dynamic pagination.
 * Previous commit had hardcoded scenario to test API working.
 */
class FakeRecipesRepositorySuccessCase : RecipesRepository {

    private val gson = Gson()
    private val jsonFileReader = JsonFileReader()

    override suspend fun getRecipes(
        page: Int,
        pageCount: Int
    ): Result<RecipeUiResponseModel, NetworkErrorApiResponse> {
        val jsonData = jsonFileReader.readSampleJsonFromResource("RecipeSampleResponse.json")
        val recipesDataResponse = gson.fromJson(jsonData, RecipeUiResponseModel::class.java)
        return Result.Success(recipesDataResponse)
    }
}