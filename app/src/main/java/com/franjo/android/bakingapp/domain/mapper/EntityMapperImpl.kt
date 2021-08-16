package com.franjo.android.bakingapp.domain.mapper

import com.franjo.android.bakingapp.data.remote.model.RecipeResponse
import com.franjo.android.bakingapp.domain.model.Ingredient
import com.franjo.android.bakingapp.domain.model.Recipe
import com.franjo.android.bakingapp.domain.model.Step
import com.franjo.android.bakingapp.presentation.model.RecipeUI
import javax.inject.Inject

class EntityMapperImpl @Inject constructor() : EntityMapper {

    override fun apiToDomain(recipe: RecipeResponse): Recipe {
        val ingredientList = recipe.ingredients?.map {
            Ingredient(
                it.quantity ?: 0.0,
                it.measure.toString(),
                it.ingredient.toString()
            )
        }
        val stepList = recipe.steps?.map {
            Step(
                it.id ?: 0,
                it.shortDescription.toString(),
                it.description.toString(),
                it.videoURL.toString(),
                it.thumbnailURL.toString()
            )
        }
        return Recipe(
            recipe.id,
            recipe.name.orEmpty(),
            recipe.servings ?: 0,
            recipe.image.orEmpty(),
            ingredientList.orEmpty(),
            stepList.orEmpty()
        )
    }

    override fun domainToUI(recipe: Recipe): RecipeUI {
        return RecipeUI(
            recipe.id,
            recipe.name,
            recipe.servings,
            recipe.image,
            recipe.ingredients,
            recipe.steps
        )
    }

    override fun domainListToUI(recipe: Recipe): List<RecipeUI> {
        val recipeList = mutableListOf<RecipeUI>()
        recipeList.add(domainToUI(recipe))
        return recipeList
    }
}