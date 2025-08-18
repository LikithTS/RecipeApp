package com.numan.feature_recipe.presentation

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.numan.feature_recipe.domain.model.RecipeUiModel
import com.numan.feature_recipe.domain.model.RecipeUiResponseModel
import com.numan.feature_recipe.domain.usecase.GetRecipeUseCase
import com.numan.feature_recipe.domain.usecase.RecipeSortingUseCase
import com.numan.feature_recipe.domain.util.FilterType
import com.numan.feature_recipe.domain.util.JsonFileReader
import com.numan.feature_recipe.domain.util.Result
import com.numan.feature_recipe.presentation.model.RecipeListUiState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeScreenViewModelTest {

    @get:Rule
    val mainRule = MainDispatcherRule(StandardTestDispatcher())
    private val getRecipeUseCase = mockk<GetRecipeUseCase>()
    private val recipeSortingUseCase = mockk<RecipeSortingUseCase>(relaxed = true)
    private val gson = Gson()
    private val jsonFileReader = JsonFileReader()

    fun getRecipesData() : List<RecipeUiModel> {
        val jsonData = jsonFileReader.readSampleJsonFromResource("RecipeSampleResponse4.json")
        val listType = object : TypeToken<List<RecipeUiModel>>() {}.type
        val recipeDataList: List<RecipeUiModel> = gson.fromJson(jsonData, listType)
        return recipeDataList
    }


    @Test
    fun `sort recipes based on name`() = runTest {

        val initial = getRecipesData()
        coEvery { getRecipeUseCase(0, 10, any()) } returns
                Result.Success(RecipeUiResponseModel(initial, initial.size))

        coEvery { recipeSortingUseCase(FilterType.NAME, any()) } answers {
            val list = secondArg<List<RecipeUiModel>>()
            list.sortedBy { it.name.lowercase() }
        }

        val viewModel = HomeScreenViewModel(getRecipeUseCase, recipeSortingUseCase)

        advanceUntilIdle()

        // Act
        viewModel.sortRecipes(FilterType.NAME)
        advanceUntilIdle()

        // Assert
        val state = viewModel.uiState.value
        assert(state is RecipeListUiState.Success)
        state as RecipeListUiState.Success

        val names = state.recipeUiList.map { it.name }
        val expected = names.sortedBy { it.lowercase() }
        assert(names == expected) { "Names are not sorted ascending: $names" }
        assert(!state.isAppending)
    }


    @Test
    fun `if sorting is set as reset, then we need to reload data again`() = runTest {

        val initial = getRecipesData()
        coEvery { getRecipeUseCase(0, 10, any()) } returns
                Result.Success(RecipeUiResponseModel(initial, initial.size))

        val viewModel = HomeScreenViewModel(getRecipeUseCase, recipeSortingUseCase)

        advanceUntilIdle()

        // Act
        viewModel.sortRecipes(FilterType.RESET)
        advanceUntilIdle()

        // Assert
        assert(viewModel.selectedFilter.value == null)
    }


}