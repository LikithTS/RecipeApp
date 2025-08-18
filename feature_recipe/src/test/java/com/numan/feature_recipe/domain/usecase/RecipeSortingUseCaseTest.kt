package com.numan.feature_recipe.domain.usecase

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.numan.feature_recipe.domain.model.RecipeUiModel
import com.numan.feature_recipe.domain.util.FilterType
import com.numan.feature_recipe.domain.util.JsonFileReader
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class RecipeSortingUseCaseTest {

    private var useCase: RecipeSortingUseCase = RecipeSortingUseCase()
    private val gson = Gson()
    private val jsonFileReader = JsonFileReader()


    fun getRecipesData() : List<RecipeUiModel> {
        val jsonData = jsonFileReader.readSampleJsonFromResource("RecipeSampleResponse4.json")
        val listType = object : TypeToken<List<RecipeUiModel>>() {}.type
        val recipeDataList: List<RecipeUiModel> = gson.fromJson(jsonData, listType)
        return recipeDataList
    }

    @Test
    fun `sort the recipe list based on difficulty and return sorted list data`() = runTest {
        val actualRecipeList = getRecipesData()
        val sortedRecipeList = useCase(FilterType.DIFFICULTY, actualRecipeList)
        Assert.assertEquals("Medium", actualRecipeList[1].difficulty)
        Assert.assertEquals("Easy", sortedRecipeList[1].difficulty)
    }

    @Test
    fun `sort the recipe list based on rating and return sorted list data`() = runTest {
        val actualRecipeList = getRecipesData()
        val sortedRecipeList = useCase(FilterType.RATING, actualRecipeList)
        Assert.assertEquals(4.6, actualRecipeList[0].rating, 0.0)
        Assert.assertEquals(4.9, sortedRecipeList[0].rating, 0.0)
    }

    @Test
    fun `sort the recipe list based on name and return sorted list data`() = runTest {
        val actualRecipeList = getRecipesData()
        val sortedRecipeList = useCase(FilterType.NAME, actualRecipeList)
        Assert.assertEquals("Classic Margherita Pizza", actualRecipeList[0].name)
        Assert.assertEquals("Beef and Broccoli Stir-Fry", sortedRecipeList[0].name)
    }
}