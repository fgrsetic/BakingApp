package com.franjo.android.bakingapp.domain.repository

import com.franjo.android.bakingapp.domain.model.Recipe
import com.franjo.android.bakingapp.domain.utils.ResultWrapper

interface RecipeRepository {
    suspend fun getAllRecipes(): ResultWrapper<Recipe>
}