package com.franjo.android.bakingapp.presentation.model

import com.franjo.android.bakingapp.domain.model.Ingredient
import com.franjo.android.bakingapp.domain.model.Step

data class RecipeUI(
    val id: Int,
    val name: String,
    val servings: Int,
    val image: String,
    val ingredients: List<Ingredient>,
    val steps: List<Step>
)
