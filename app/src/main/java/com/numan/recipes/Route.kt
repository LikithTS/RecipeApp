package com.numan.recipes

import kotlinx.serialization.Serializable

/**
 * Define routes for navigation
 */
@Serializable
sealed interface Route {

    @Serializable
    data object RecipeHomeScreen: Route

    @Serializable
    data class DetailRecipeScreen(val id: Int): Route
}