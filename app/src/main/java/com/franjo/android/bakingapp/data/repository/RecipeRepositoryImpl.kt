package com.franjo.android.bakingapp.data.repository

import com.franjo.android.bakingapp.data.remote.RemoteDataSource
import com.franjo.android.bakingapp.domain.model.Recipe
import com.franjo.android.bakingapp.domain.repository.RecipeRepository
import com.franjo.android.bakingapp.domain.utils.ResultWrapper
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val dataSource: RemoteDataSource
) : RecipeRepository {

    override suspend fun getAllRecipes(): ResultWrapper<Recipe> = dataSource.getAllRecipes()

}