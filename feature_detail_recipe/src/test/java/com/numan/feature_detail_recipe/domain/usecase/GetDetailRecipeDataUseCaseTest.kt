package com.numan.feature_detail_recipe.domain.usecase

import com.numan.feature_detail_recipe.data.repository.FakeDetailRecipeRepositoryFailureCase
import com.numan.feature_detail_recipe.data.repository.FakeDetailRecipeRepositoryFailureCase2
import com.numan.feature_detail_recipe.data.repository.FakeDetailRecipeRepositoryFailureCase3
import com.numan.feature_detail_recipe.data.repository.FakeDetailRecipeRepositorySuccessCase
import com.numan.feature_detail_recipe.domain.repository.RecipeDetailRepository
import com.numan.feature_detail_recipe.domain.util.NetworkErrorApiResponse
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import com.numan.feature_detail_recipe.domain.util.Result

/**
 * I have followed TDD in feature_recipe module.
 * I have not followed TDD in this module. Just to show how things can be different.
 * When we follow TDD we need some time to think about all the use case and the corner initially.
 */
class GetDetailRecipeDataUseCaseTest {

    private lateinit var getDetailRecipeDataUseCase: GetDetailRecipeDataUseCase

    private lateinit var recipeDetailRepository: RecipeDetailRepository

    @Test
    fun `returns bad request when we send some invalid id in the path`() = runTest {
        recipeDetailRepository = FakeDetailRecipeRepositoryFailureCase()
        getDetailRecipeDataUseCase = GetDetailRecipeDataUseCase(recipeDetailRepository)

        when (val responseData = getDetailRecipeDataUseCase(id = 1234567890)) {
            is Result.Success -> {
                Assert.fail("Expected failure but got success: ${responseData.data}")
            }

            is Result.Error -> {
                Assert.assertEquals(NetworkErrorApiResponse.BAD_REQUEST, responseData.error)
            }
        }
    }

    @Test
    fun `returns request timeout when api is taking too much time to return response`() = runTest {
        recipeDetailRepository = FakeDetailRecipeRepositoryFailureCase2()
        getDetailRecipeDataUseCase = GetDetailRecipeDataUseCase(recipeDetailRepository)

        when (val responseData = getDetailRecipeDataUseCase(id = 1)) {
            is Result.Success -> {
                Assert.fail("Expected failure but got success: ${responseData.data}")
            }

            is Result.Error -> {
                Assert.assertEquals(NetworkErrorApiResponse.REQUEST_TIMEOUT, responseData.error)
            }
        }
    }

    @Test
    fun `returns not found when api is not available in backend`() = runTest {
        recipeDetailRepository = FakeDetailRecipeRepositoryFailureCase3()
        getDetailRecipeDataUseCase = GetDetailRecipeDataUseCase(recipeDetailRepository)

        when (val responseData = getDetailRecipeDataUseCase(id = 1)) {
            is Result.Success -> {
                Assert.fail("Expected failure but got success: ${responseData.data}")
            }

            is Result.Error -> {
                Assert.assertEquals(NetworkErrorApiResponse.NOT_FOUND, responseData.error)
            }
        }
    }

    @Test
    fun `returns recipe data when when valid id is sent`() = runTest {
        recipeDetailRepository = FakeDetailRecipeRepositorySuccessCase()
        getDetailRecipeDataUseCase = GetDetailRecipeDataUseCase(recipeDetailRepository)

        when (val responseData = getDetailRecipeDataUseCase(id = 1)) {
            is Result.Success -> {
                Assert.assertEquals(1, responseData.data?.id)
            }

            is Result.Error -> {
                Assert.fail("Expected success but got error: ${responseData.error}")
            }
        }
    }

    @Test
    fun `returns failure not found data error when when we can't find valid id`() = runTest {
        recipeDetailRepository = FakeDetailRecipeRepositorySuccessCase()
        getDetailRecipeDataUseCase = GetDetailRecipeDataUseCase(recipeDetailRepository)

        when (val responseData = getDetailRecipeDataUseCase(id = 2)) {
            is Result.Success -> {
                Assert.fail("Expected failure but got success: ${responseData.data}")
            }

            is Result.Error -> {
                Assert.assertEquals(NetworkErrorApiResponse.NOT_FOUND, responseData.error)
            }
        }
    }
}