package com.numan.feature_recipe.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.numan.feature_recipe.domain.usecase.GetRecipeUseCase
import com.numan.feature_recipe.presentation.model.RecipeListUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.numan.feature_recipe.domain.util.Result

/**
 * Viewmodel to act like bridge between our business logic to UI.
 * We're handling success and failure state and updating to UI state.
 */
class HomeScreenViewModel(
    private val getRecipeUseCase: GetRecipeUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<RecipeListUiState>(RecipeListUiState.Ideal)
    val uiState = _uiState.asStateFlow()

    /**
     * To get recipe data using the api.
     */
    fun getRecipeData() {
        _uiState.value = RecipeListUiState.Loading
        viewModelScope.launch {
            val dataResponse = getRecipeUseCase(limit = 10, skip = 0)

            when(dataResponse) {
                is Result.Success -> {
                    //We are already handling empty response in use case. So no need to worry here.
                    // Since our Success have nullable. We need to pass empty string here again
                    val data = dataResponse.data ?: emptyList()
                    _uiState.value = RecipeListUiState.Success(
                        data
                    )
                }

                is Result.Error -> {
                    _uiState.value = RecipeListUiState.Error(dataResponse.error)
                }
            }
        }
    }
}