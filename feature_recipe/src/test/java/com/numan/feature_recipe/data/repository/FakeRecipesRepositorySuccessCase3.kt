package com.numan.feature_recipe.data.repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.numan.feature_recipe.domain.model.RecipeUiModel
import com.numan.feature_recipe.domain.repository.RecipesRepository
import com.numan.feature_recipe.domain.util.APIResponse
import com.numan.feature_recipe.domain.util.JsonFileReader
import com.numan.feature_recipe.domain.util.Result

class FakeRecipesRepositorySuccessCase3 : RecipesRepository {

    private val gson = Gson()
    private val jsonFileReader = JsonFileReader()

    override suspend fun getRecipes(
        limit: Int,
        skip: Int
    ): Result<List<RecipeUiModel>, APIResponse> {
        val jsonData = jsonFileReader.readSampleJsonFromResource("RecipeSampleResponse3.json")
        val listType = object : TypeToken<List<RecipeUiModel>>() {}.type
        val recipesDataResponse: List<RecipeUiModel> = gson.fromJson(jsonData, listType)
        return Result.Success(recipesDataResponse)
    }
}