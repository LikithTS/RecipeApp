package com.numan.feature_recipe.domain.util

import com.numan.feature_recipe.R

/**
 * Enum to show filter options in home screen
 */
enum class FilterType(val stringId: Int) {

    NAME(R.string.name),
    DIFFICULTY(R.string.difficulty),
    RATING(R.string.rating),
    RESET(R.string.reset)

}