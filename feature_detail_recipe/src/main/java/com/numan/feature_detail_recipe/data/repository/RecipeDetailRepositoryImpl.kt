package com.numan.feature_detail_recipe.data.repository

import com.numan.feature_detail_recipe.data.remote.RecipeDetailAPIInterface
import com.numan.feature_detail_recipe.data.util.toDetailRecipeUiModel
import com.numan.feature_detail_recipe.domain.model.DetailRecipeUiModel
import com.numan.feature_detail_recipe.domain.repository.RecipeDetailRepository
import com.numan.feature_detail_recipe.domain.util.NetworkErrorApiResponse
import com.numan.feature_detail_recipe.domain.util.Result
import kotlinx.coroutines.ensureActive
import java.lang.Exception
import kotlin.coroutines.coroutineContext

/**
 * Actual implementation of the repository method
 * I am converting the response to UI model here.
 * Data flow should be like this in clean architecture.
 * Data -> Module <- Presentation
 */
class RecipeDetailRepositoryImpl(
    private val recipeDetailAPIInterface: RecipeDetailAPIInterface
) : RecipeDetailRepository {

    override suspend fun getDetailRecipeBasedOnId(id: Int): Result<DetailRecipeUiModel, NetworkErrorApiResponse> {
        return try {
            val recipesResponse = recipeDetailAPIInterface.getRecipeDetailData(id = id)
            if (recipesResponse.isSuccessful) {
                // We need to convert the response to UI model.
                val detailRecipeUiModel = recipesResponse.body()?.toDetailRecipeUiModel()
                return Result.Success(detailRecipeUiModel)
            } else {
                //Handle error response.
                //We can add some more network error here. To keep the app simple I have handled few of the error case.
                val errorCode = recipesResponse.code()
                when (errorCode) {
                    401 -> Result.Error(NetworkErrorApiResponse.UNAUTHORISED)
                    404 -> Result.Error(NetworkErrorApiResponse.BAD_REQUEST)
                    else -> Result.Error(NetworkErrorApiResponse.FAILURE)
                }
            }
        } catch (e: Exception) {
            //Since we're catching generic exception. If coroutines throws cancellation exception.
            //We need to throw the exception back or handle it.
            //I'm handling exception here.
            coroutineContext.ensureActive()
            Result.Error(NetworkErrorApiResponse.GenericException(e.message ?: "Failure"))
        }
    }
}