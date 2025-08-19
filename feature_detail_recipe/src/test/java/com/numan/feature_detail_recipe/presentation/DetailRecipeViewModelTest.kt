package com.numan.feature_detail_recipe.presentation

import com.google.gson.Gson
import com.numan.feature_detail_recipe.domain.model.DetailRecipeUiModel
import com.numan.feature_detail_recipe.domain.usecase.GetDetailRecipeDataUseCase
import com.numan.feature_detail_recipe.domain.util.JsonFileReader
import com.numan.feature_detail_recipe.domain.util.NetworkErrorApiResponse
import com.numan.feature_detail_recipe.domain.util.Result
import com.numan.feature_detail_recipe.presentation.model.DetailRecipeUiState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DetailRecipeViewModelTest {

    private lateinit var viewModel: DetailRecipeViewModel
    private val getDetailRecipeDataUseCase = mockk<GetDetailRecipeDataUseCase>()
    private val gson = Gson()
    private val jsonFileReader = JsonFileReader()
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {

        Dispatchers.setMain(testDispatcher)
        viewModel = DetailRecipeViewModel(getDetailRecipeDataUseCase)
    }

    private fun getDetailRecipeBasedOnId(id: Int): Result<DetailRecipeUiModel, NetworkErrorApiResponse> {
        val jsonData = jsonFileReader.readSampleJsonFromResource("RecipeSampleResponse.json")
        val recipeDataResponse = gson.fromJson(jsonData, DetailRecipeUiModel::class.java)
        return if(id == recipeDataResponse.id) {
            Result.Success(recipeDataResponse)
        } else {
            Result.Error(NetworkErrorApiResponse.NOT_FOUND)
        }
    }

    @Test
    fun `fetch success response for recipe data with valid ID is sent in path`() = runTest {

        val id = 1
        coEvery {
            getDetailRecipeDataUseCase(id)
        } returns getDetailRecipeBasedOnId(id)

        viewModel.getDetailRecipeDataById(id)

        advanceUntilIdle()

        val state = viewModel.uiState.value
        assert(state is DetailRecipeUiState.Success)
        state as DetailRecipeUiState.Success

        val recipeName = state.detailRecipeModel.name

        Assert.assertEquals(
            "Classic Margherita Pizza",
            recipeName
        )
    }

    @Test
    fun `fetch failure response for recipe data with in valid ID is sent in path`() = runTest {

        val id = 2
        coEvery {
            getDetailRecipeDataUseCase(id)
        } returns getDetailRecipeBasedOnId(id)

        viewModel.getDetailRecipeDataById(id)

        advanceUntilIdle()

        val state = viewModel.uiState.value
        assert(state is DetailRecipeUiState.Failure)
        state as DetailRecipeUiState.Failure

        val failureMessage = state.message

        Assert.assertEquals(
            NetworkErrorApiResponse.NOT_FOUND,
            failureMessage
        )
    }
}