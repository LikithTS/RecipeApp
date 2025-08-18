package com.numan.feature_recipe.domain.util

/**
 * To sort the list based on difficulty level.
 * Since we're using enum ranking will be automatically assigned based on the order.
 * So no need to define order again.
 * Just maintain same when adding new levels.
 */
enum class RecipeDifficultyLevel {
    EASY,
    MEDIUM,
    HARD
}