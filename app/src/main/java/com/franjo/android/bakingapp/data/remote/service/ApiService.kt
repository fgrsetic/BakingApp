package com.franjo.android.bakingapp.data.remote.service

import com.franjo.android.bakingapp.data.remote.model.RecipeResponse
import retrofit2.http.GET

interface ApiService {

    @GET("baking.json")
    suspend fun getAllRecipes(): RecipeResponse
}