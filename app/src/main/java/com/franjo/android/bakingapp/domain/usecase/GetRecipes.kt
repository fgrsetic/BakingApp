package com.franjo.android.bakingapp.domain.usecase

import com.franjo.android.bakingapp.domain.repository.RecipeRepository
import javax.inject.Inject

class GetRecipes @Inject constructor(
    private val repository: RecipeRepository
) {

    suspend fun execute() = repository.getAllRecipes()
}