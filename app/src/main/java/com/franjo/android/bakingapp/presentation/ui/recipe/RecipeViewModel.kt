package com.franjo.android.bakingapp.presentation.ui.recipe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.franjo.android.bakingapp.domain.di.MainDispatcher
import com.franjo.android.bakingapp.domain.mapper.EntityMapper
import com.franjo.android.bakingapp.domain.usecase.GetRecipes
import com.franjo.android.bakingapp.domain.utils.ResultWrapper
import com.franjo.android.bakingapp.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    @MainDispatcher dispatcher: CoroutineDispatcher,
    private var mapper: EntityMapper,
    private val getRecipes: GetRecipes
) : BaseViewModel(dispatcher) {

    private val _recipesViewState = MutableLiveData<RecipeViewState>()
    val recipesViewState: LiveData<RecipeViewState> = _recipesViewState

    fun setStateEvent(viewEvent: RecipeViewEvent) {
        viewModelScope.launch {
            when (viewEvent) {
                is RecipeViewEvent.GetRecipesEvent -> getRecipesState()
            }
        }
    }

    private suspend fun getRecipesState() {
        when (val result = getRecipes.execute()) {
            is ResultWrapper.Success -> {
                val recipeUi = mapper.domainListToUI(result.data)
                _recipesViewState.postValue(RecipeViewState.Success(recipeUi))
            }
            is ResultWrapper.Error -> {
                _recipesViewState.postValue(RecipeViewState.Error(result.error.toString()))
            }
        }
    }
}