package com.numan.feature_recipe.domain.util

/**
 * This interface can be scaled to have error message which is received from the API response or
 * have a custom error message which supports localisation. This way we can extend this class to our need.
 * Here I am kept it constant only and error message from exception. Just to keep project simple and easy to complete.
 * API response will be one of the error type in the app.
 * We can create different error interface for different needs in our app.
 */
sealed interface APIResponse : Error {

    data object BAD_REQUEST : APIResponse
    data object EMPTY_RESPONSE : APIResponse
    data object FAILURE : APIResponse
    data object NOT_FOUND : APIResponse
    data object UNAUTHORISED : APIResponse
    data object REQUEST_TIMEOUT : APIResponse
    data class GenericException(val errorMessage: String) : APIResponse
}