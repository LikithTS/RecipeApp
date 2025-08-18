package com.numan.feature_recipe.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.numan.feature_recipe.domain.model.RecipeUiModel
import com.numan.feature_recipe.domain.usecase.GetRecipeUseCase
import com.numan.feature_recipe.domain.usecase.RecipeSortingUseCase
import com.numan.feature_recipe.domain.util.CustomPaginationLib
import com.numan.feature_recipe.domain.util.FilterType
import com.numan.feature_recipe.presentation.model.RecipeListUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Viewmodel to act like bridge between our business logic to UI.
 * We're handling success and failure state and updating to UI state.
 */
class HomeScreenViewModel(
    private val getRecipeUseCase: GetRecipeUseCase,
    private val recipeSortingUseCase: RecipeSortingUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<RecipeListUiState>(RecipeListUiState.Ideal)
    val uiState = _uiState.asStateFlow()

    private val _selectedFilter = MutableStateFlow<FilterType?>(null)
    val selectedFilter = _selectedFilter.asStateFlow()


    private val pageCount = 10

    private val completeRecipeList = mutableListOf<RecipeUiModel>()

    private val paginator = CustomPaginationLib(
        initialKey = 0,
        onLoadUpdated = { isLoading ->
            if (isLoading) {
                // Showing loader in center
                if (completeRecipeList.isEmpty()) {
                    _uiState.value = RecipeListUiState.Loading
                } else {
                    // Now center loading will not be shown, instead loading at the bottom will be shown.
                    _uiState.value = RecipeListUiState.Success(
                        recipeUiList = completeRecipeList.toList(),
                        isAppending = true
                    )
                }
            } else {
                // Loading finished; keep whatever content we have without footer
                if (completeRecipeList.isNotEmpty()) {
                    _uiState.value = RecipeListUiState.Success(
                        recipeUiList = completeRecipeList.toList(),
                        isAppending = false
                    )
                }
            }
        },
        onRequest = { currentPage ->
            getRecipeUseCase(page = currentPage, pageCount = pageCount)
        },
        getNextKey = { currentPage, _ ->
            currentPage + 1
        },
        onError = { error ->
            if (completeRecipeList.isNotEmpty()) {
                _uiState.value = RecipeListUiState.Success(
                    recipeUiList = completeRecipeList.toList(),
                    isAppending = false
                )
            } else {
                _uiState.value = RecipeListUiState.Failure(error)
            }
        },
        onSuccess = { productsResponse, nextPage ->
            //Handling success case
            completeRecipeList += productsResponse.recipeUiMode

            viewModelScope.launch {
                val displayList = _selectedFilter.value?.let { filter ->
                    // apply current filter via use case
                    recipeSortingUseCase(filter, completeRecipeList)
                } ?: completeRecipeList.toList()

                _uiState.value = RecipeListUiState.Success(
                    recipeUiList = displayList,
                    isAppending = false
                )
            }
        },
        endReached = { currentPage, response ->
            (currentPage * pageCount) >= response.totalItem
        }
    )

    init {
        loadNextItems()
    }

    fun loadNextItems() {
        viewModelScope.launch {
            paginator.loadNextItems()
        }
    }

    /**
     * Reset paging will be called when we do sorting or filtering.
     */
    fun resetPaging() {
        completeRecipeList.clear()
        _uiState.value = RecipeListUiState.Ideal
        paginator.reset()
        loadNextItems()
    }

    /**
     * Method is used to sort recipes in the list
     */
    fun sortRecipes(filterType: FilterType) {
        _selectedFilter.value = filterType
        val current = _uiState.value
        if (current is RecipeListUiState.Success || completeRecipeList.isNotEmpty()) {
            when (filterType) {
                FilterType.RESET -> {
                    _selectedFilter.value = null
                    resetPaging()
                }
                else -> {
                    viewModelScope.launch {
                        val sorted = recipeSortingUseCase(filterType, completeRecipeList)
                        _uiState.value = RecipeListUiState.Success(
                            recipeUiList = sorted,
                            isAppending = false
                        )
                    }
                }
            }
        }
    }

}