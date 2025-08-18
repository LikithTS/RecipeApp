package com.numan.feature_recipe.domain.usecase

import com.numan.feature_recipe.domain.model.RecipeUiResponseModel
import com.numan.feature_recipe.domain.repository.RecipesRepository
import com.numan.feature_recipe.domain.util.Result
import com.numan.feature_recipe.domain.util.CustomRecipeErrorResponse
import com.numan.feature_recipe.domain.util.Error

/**
 * Created a empty use case. Since we're follow TDD.
 * We can avoid this use case. Since we're not doing anything here.
 * To keep our approach similar and also keeping scalability in mind.
 * I am going head with use case. We can use this use to modify the data later.
 * Added a check to see if we've reached the end point. If we have reached the end point avoid new api call and return error here.
 */
class GetRecipeUseCase(
    private val recipesRepository: RecipesRepository
) {

    suspend operator fun invoke(
        page: Int = 0,
        pageCount: Int = 10,
        totalItemCount: Int = 0
    ): Result<RecipeUiResponseModel, Error> {
        return if (totalItemCount > 0 && (page * pageCount) == totalItemCount) {
            Result.Error(CustomRecipeErrorResponse.NO_NEW_ITEM)
        } else {
            recipesRepository.getRecipes(page = page, pageCount = pageCount)
        }
    }
}