package com.numan.feature_recipe.domain.util

/**
 * One more error to handle custom recipe errors. For now I am just sending no new item. We can later add some more error object.
 */
sealed interface CustomRecipeErrorResponse : Error {

    data object NO_NEW_ITEM : CustomRecipeErrorResponse
}