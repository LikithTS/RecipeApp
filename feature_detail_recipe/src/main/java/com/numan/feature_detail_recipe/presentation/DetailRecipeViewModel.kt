package com.numan.feature_detail_recipe.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.numan.feature_detail_recipe.domain.usecase.GetDetailRecipeDataUseCase
import com.numan.feature_detail_recipe.domain.util.Result
import com.numan.feature_detail_recipe.presentation.model.DetailRecipeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailRecipeViewModel(
    private val getDetailRecipeDataUseCase: GetDetailRecipeDataUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<DetailRecipeUiState>(DetailRecipeUiState.Ideal)
    val uiState = _uiState.asStateFlow()

    /**
     * Get the recipe based on id from the api.
     */
    fun getDetailRecipeDataById(id: Int) {
        _uiState.value = DetailRecipeUiState.Loading
        viewModelScope.launch {
            when (val response = getDetailRecipeDataUseCase(id)) {
                is Result.Success -> {
                    response.data?.let {
                        _uiState.value = DetailRecipeUiState.Success(response.data)
                    }
                }

                is Result.Error -> {
                    _uiState.value = DetailRecipeUiState.Failure(response.error)
                }
            }
        }
    }
}