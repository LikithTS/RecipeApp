package com.numan.feature_detail_recipe.domain.util

typealias RootError = Error

/**
 * Result will act like a generic class. Which can used in any api response.
 * Here on purpose I have kept result data as nullable.
 * Since some of the api calls like PUT won't return anything. Also this totally depends on backend
 */
sealed interface Result<out D, out E : RootError> {
    data class Success<out D, out E : RootError>(val data: D?) : Result<D, E>
    data class Error<out D, out E : RootError>(val error: E) : Result<D, E>
}