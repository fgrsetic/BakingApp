package com.franjo.android.bakingapp.presentation.ui.recipe

import com.franjo.android.bakingapp.presentation.model.RecipeUI

sealed class RecipeViewState {
    data class Success(val recipes: List<RecipeUI>) : RecipeViewState()
    data class Error(val message: String) : RecipeViewState()
    object Loading : RecipeViewState()

    // Helper methods that make it easy to access
    // the sealed class contents in xml data binding
    fun toRecipes(): List<RecipeUI> = when (this) {
        is Success -> this.recipes
        else -> emptyList()
    }

    fun toError(): String? = if (this is Error) this.message else null

    fun isLoading(): Boolean? = if (this is Loading) true else null

}

