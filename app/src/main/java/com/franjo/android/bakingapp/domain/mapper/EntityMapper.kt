package com.franjo.android.bakingapp.domain.mapper

import com.franjo.android.bakingapp.data.remote.model.RecipeResponse
import com.franjo.android.bakingapp.domain.model.Recipe
import com.franjo.android.bakingapp.domain.utils.ResultWrapper
import com.franjo.android.bakingapp.presentation.model.RecipeUI

interface EntityMapper {
    fun apiToDomain(recipe: RecipeResponse): Recipe
    fun domainToUI(recipe: Recipe): RecipeUI
    fun domainListToUI(recipe: Recipe): List<RecipeUI>
}