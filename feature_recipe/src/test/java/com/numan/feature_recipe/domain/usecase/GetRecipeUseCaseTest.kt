package com.numan.feature_recipe.domain.usecase

import org.junit.Test

/**
 * Following TDD approach.
 * Initially we will define all the test cases we think which is valid and needs to part of the class.
 * Logic will be add later.
 */
class GetRecipeUseCaseTest {

    private lateinit var useCase: GetRecipeUseCase

    @Test
    fun `returns first 10 list of recipes when we send limit 10 and the skip 0`() {

    }

    @Test
    fun `returns item from 11 to 20 when we send limit 10 and skip 10`() {

    }

    @Test
    fun `returns item from 0 to 20 when we send limit 20 and skip 0`() {

    }

    @Test
    fun `returns 0 items when we send limit 0 and skip will be size of the final list`() {

    }

    @Test
    fun `returns failure when api call fails due to network error`() {

    }


}