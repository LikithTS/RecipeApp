package com.numan.feature_recipe.data.repository

import com.numan.feature_recipe.data.remote.RecipeApiInterface
import com.numan.feature_recipe.data.util.toRecipeUiModel
import com.numan.feature_recipe.domain.model.RecipeUiModel
import com.numan.feature_recipe.domain.repository.RecipesRepository
import com.numan.feature_recipe.domain.util.APIResponse
import com.numan.feature_recipe.domain.util.Result
import kotlinx.coroutines.ensureActive
import java.lang.Exception
import kotlin.coroutines.coroutineContext

/**
 * Actual implementation
 */
class RecipesRepositoryImpl(
    private val recipeApiInterface: RecipeApiInterface
) : RecipesRepository {

    override suspend fun getRecipes(
        limit: Int,
        skip: Int
    ): Result<List<RecipeUiModel>, APIResponse> {
        return try {
            val recipesResponse = recipeApiInterface.getRecipesData(limit, skip)
            if (recipesResponse.isSuccessful) {
                // We need to convert the response to UI model.
                val recipeUiModel = recipesResponse.body()?.recipes?.map { recipe ->
                    recipe.toRecipeUiModel()
                }
                return Result.Success(recipeUiModel)
            } else {
                //Handle error response.
                //We can add some more network error here. To keep the app simple I have handled few of the error case.
                val errorCode = recipesResponse.code()
                when (errorCode) {
                    401 -> Result.Error(APIResponse.UNAUTHORISED)
                    404 -> Result.Error(APIResponse.BAD_REQUEST)
                    else -> Result.Error(APIResponse.FAILURE)
                }
            }
        } catch (e: Exception) {
            //Since we're catching generic exception. If coroutines throws cancellation exception.
            //We need to throw the exception back or handle it.
            //I'm handling exception here.
            coroutineContext.ensureActive()
            Result.Error(APIResponse.GenericException(e.message ?: "Failure"))
        }
    }
}