package com.numan.feature_recipe.domain.usecase

import com.numan.feature_recipe.data.repository.FakeRecipesRepositoryFailureCase2
import com.numan.feature_recipe.data.repository.FakeRecipesRepositoryFailureCase3
import com.numan.feature_recipe.data.repository.FakeRecipesRepositoryForFailureCase
import com.numan.feature_recipe.data.repository.FakeRecipesRepositorySuccessCase
import com.numan.feature_recipe.data.repository.FakeRecipesRepositorySuccessCase2
import com.numan.feature_recipe.data.repository.FakeRecipesRepositorySuccessCase3
import com.numan.feature_recipe.domain.repository.RecipesRepository
import com.numan.feature_recipe.domain.util.APIResponse
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import com.numan.feature_recipe.domain.util.Result

/**
 * Following TDD approach.
 * Initially we will define all the test cases we think which is valid and needs to part of the class.
 * Logic will be add later.
 */
class GetRecipeUseCaseTest {

    private lateinit var useCase: GetRecipeUseCase
    private lateinit var recipesRepository: RecipesRepository

    @Test
    fun `returns first 10 list of recipes when we send limit 10 and the skip 0`() = runTest{
        recipesRepository = FakeRecipesRepositorySuccessCase()
        useCase = GetRecipeUseCase(recipesRepository)

        when(val responseData = useCase(limit = 10, skip = 0)) {
            is Result.Success -> {
                Assert.assertEquals(10, responseData.data?.size)
                Assert.assertEquals(1, responseData.data?.get(0)?.id)
                Assert.assertEquals(10, responseData.data?.get(9)?.id)
            }
            is Result.Error -> {
                Assert.fail("Expected success but got error: ${responseData.error}")
            }
        }
    }

    @Test
    fun `returns item from 11 to 20 when we send limit 10 and skip 10`() = runTest{
        recipesRepository = FakeRecipesRepositorySuccessCase2()
        useCase = GetRecipeUseCase(recipesRepository)

        when(val responseData = useCase(limit = 10, skip = 10)) {
            is Result.Success -> {
                Assert.assertEquals(10, responseData.data?.size)
                Assert.assertEquals(11, responseData.data?.get(0)?.id)
                Assert.assertEquals(20, responseData.data?.get(9)?.id)
            }
            is Result.Error -> {
                Assert.fail("Expected success but got error: ${responseData.error}")
            }
        }
    }

    @Test
    fun `returns item from 0 to 20 when we send limit 20 and skip 0`() = runTest{
        recipesRepository = FakeRecipesRepositorySuccessCase3()
        useCase = GetRecipeUseCase(recipesRepository)

        when(val responseData = useCase(limit = 20, skip = 0)) {
            is Result.Success -> {
                Assert.assertEquals(20, responseData.data?.size)
                Assert.assertEquals(1, responseData.data?.get(0)?.id)
                Assert.assertEquals(20, responseData.data?.get(19)?.id)
            }
            is Result.Error -> {
                Assert.fail("Expected success but got error: ${responseData.error}")
            }
        }
    }

    @Test
    fun `returns 0 items when we send limit 10 and skip will be size of the final list`() = runTest{
        recipesRepository = FakeRecipesRepositoryFailureCase3()
        useCase = GetRecipeUseCase(recipesRepository)

        when(val responseData = useCase(limit = 10, skip = 50)) {
            is Result.Success -> {
                Assert.fail("Expected failure but got success: ${responseData.data}")
            }
            is Result.Error -> {
                Assert.assertEquals(APIResponse.EMPTY_RESPONSE, responseData.error)
            }
        }
    }

    @Test
    fun `returns failure when api call fails due to network error`() = runTest{
        recipesRepository = FakeRecipesRepositoryForFailureCase()
        useCase = GetRecipeUseCase(recipesRepository)

        when(val responseData = useCase(limit = 10, skip = 50)) {
            is Result.Success -> {
                Assert.fail("Expected failure but got success: ${responseData.data}")
            }
            is Result.Error -> {
                Assert.assertEquals(APIResponse.FAILURE, responseData.error)
            }
        }
    }

    @Test
    fun `returns api not found error when api call fails due to api not found exception`() = runTest{
        recipesRepository = FakeRecipesRepositoryFailureCase2()
        useCase = GetRecipeUseCase(recipesRepository)

        when(val responseData = useCase(limit = 10, skip = 0)) {
            is Result.Success -> {
                Assert.fail("Expected failure but got success: ${responseData.data}")
            }
            is Result.Error -> {
                Assert.assertEquals(APIResponse.NOT_FOUND, responseData.error)
            }
        }
    }
}