package com.numan.feature_detail_recipe.data.repository

import com.google.gson.Gson
import com.numan.feature_detail_recipe.domain.model.DetailRecipeUiModel
import com.numan.feature_detail_recipe.domain.repository.RecipeDetailRepository
import com.numan.feature_detail_recipe.domain.util.JsonFileReader
import com.numan.feature_detail_recipe.domain.util.NetworkErrorApiResponse
import com.numan.feature_detail_recipe.domain.util.Result

class FakeDetailRecipeRepositorySuccessCase : RecipeDetailRepository{

    private val gson = Gson()
    private val jsonFileReader = JsonFileReader()

    override suspend fun getDetailRecipeBasedOnId(id: Int): Result<DetailRecipeUiModel, NetworkErrorApiResponse> {
        val jsonData = jsonFileReader.readSampleJsonFromResource("RecipeSampleResponse.json")
        val recipeDataResponse = gson.fromJson(jsonData, DetailRecipeUiModel::class.java)
        return if(id == recipeDataResponse.id) {
            Result.Success(recipeDataResponse)
        } else {
            Result.Error(NetworkErrorApiResponse.NOT_FOUND)
        }
    }
}