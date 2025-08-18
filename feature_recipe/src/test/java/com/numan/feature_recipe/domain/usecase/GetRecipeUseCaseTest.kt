package com.numan.feature_recipe.domain.usecase

import com.numan.feature_recipe.data.repository.FakeRecipesRepositoryFailureCase2
import com.numan.feature_recipe.data.repository.FakeRecipesRepositoryFailureCase3
import com.numan.feature_recipe.data.repository.FakeRecipesRepositoryForFailureCase
import com.numan.feature_recipe.data.repository.FakeRecipesRepositorySuccessCase
import com.numan.feature_recipe.data.repository.FakeRecipesRepositorySuccessCase2
import com.numan.feature_recipe.data.repository.FakeRecipesRepositorySuccessCase3
import com.numan.feature_recipe.domain.repository.RecipesRepository
import com.numan.feature_recipe.domain.util.CustomRecipeErrorResponse
import com.numan.feature_recipe.domain.util.NetworkErrorApiResponse
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import com.numan.feature_recipe.domain.util.Result

/**
 * Following TDD approach.
 * Initially we will define all the test cases we think which is valid and needs to part of the class.
 * Logic will be add later.
 * Updated parameter names and response handling
 */
class GetRecipeUseCaseTest {

    private lateinit var useCase: GetRecipeUseCase
    private lateinit var recipesRepository: RecipesRepository

    @Test
    fun `returns first 10 list of recipes when we send limit 10 and the skip 0`() = runTest{
        recipesRepository = FakeRecipesRepositorySuccessCase()
        useCase = GetRecipeUseCase(recipesRepository)

        when(val responseData = useCase(page = 1, pageCount = 10, totalItemCount = 0)) {
            is Result.Success -> {
                Assert.assertEquals(10, responseData.data?.recipeUiMode?.size)
                Assert.assertEquals(1, responseData.data?.recipeUiMode?.get(0)?.id)
                Assert.assertEquals(10, responseData.data?.recipeUiMode?.get(9)?.id)
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

        when(val responseData = useCase(page = 2, pageCount = 10, totalItemCount = 50)) {
            is Result.Success -> {
                Assert.assertEquals(10, responseData.data?.recipeUiMode?.size)
                Assert.assertEquals(11, responseData.data?.recipeUiMode?.get(0)?.id)
                Assert.assertEquals(20, responseData.data?.recipeUiMode?.get(9)?.id)
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

        when(val responseData = useCase(page = 1, pageCount = 20, totalItemCount = 0)) {
            is Result.Success -> {
                Assert.assertEquals(20, responseData.data?.recipeUiMode?.size)
                Assert.assertEquals(1, responseData.data?.recipeUiMode?.get(0)?.id)
                Assert.assertEquals(20, responseData.data?.recipeUiMode?.get(19)?.id)
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

        when(val responseData = useCase(page = 5, pageCount = 10, totalItemCount = 50)) {
            is Result.Success -> {
                Assert.fail("Expected failure but got success: ${responseData.data}")
            }
            is Result.Error -> {
                Assert.assertEquals(CustomRecipeErrorResponse.NO_NEW_ITEM, responseData.error)
            }
        }
    }

    @Test
    fun `returns failure when api call fails due to network error`() = runTest{
        recipesRepository = FakeRecipesRepositoryForFailureCase()
        useCase = GetRecipeUseCase(recipesRepository)

        when(val responseData = useCase(page = 10, pageCount = 50, totalItemCount = 0)) {
            is Result.Success -> {
                Assert.fail("Expected failure but got success: ${responseData.data}")
            }
            is Result.Error -> {
                Assert.assertEquals(NetworkErrorApiResponse.FAILURE, responseData.error)
            }
        }
    }

    @Test
    fun `returns api not found error when api call fails due to api not found exception`() = runTest{
        recipesRepository = FakeRecipesRepositoryFailureCase2()
        useCase = GetRecipeUseCase(recipesRepository)

        when(val responseData = useCase(page = 1, pageCount = 10, totalItemCount = 0)) {
            is Result.Success -> {
                Assert.fail("Expected failure but got success: ${responseData.data}")
            }
            is Result.Error -> {
                Assert.assertEquals(NetworkErrorApiResponse.NOT_FOUND, responseData.error)
            }
        }
    }
}