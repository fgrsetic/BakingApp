package com.franjo.android.bakingapp.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RecipeResponse(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String?,
    @Json(name = "servings") val servings: Int?,
    @Json(name = "image") val image: String?,
    @Json(name = "ingredients") val ingredients: List<IngredientDTO>?,
    @Json(name = "steps") val steps: List<StepDTO>?
)
