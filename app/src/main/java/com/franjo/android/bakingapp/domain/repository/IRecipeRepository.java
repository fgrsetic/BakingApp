package com.franjo.android.bakingapp.domain.repository;

import com.franjo.android.bakingapp.domain.model.RecipeEntity;

import java.util.List;

import io.reactivex.rxjava3.core.Single;


public interface IRecipeRepository {
    Single<List<RecipeEntity>> getAllRecipes();
}
