package com.franjo.android.bakingapp.presentation.ui.recipe

sealed class RecipeViewEvent {
    object GetRecipesEvent : RecipeViewEvent()
}
