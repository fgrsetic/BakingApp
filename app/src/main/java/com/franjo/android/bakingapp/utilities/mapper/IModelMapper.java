package com.franjo.android.bakingapp.utilities.mapper;

import com.franjo.android.bakingapp.data.network.model.ApiRecipe;
import com.franjo.android.bakingapp.domain.model.RecipeEntity;
import com.franjo.android.bakingapp.presentation.model.RecipeUI;

import java.util.List;

public interface IModelMapper {

    RecipeEntity apiToDomain(ApiRecipe apiRecipe);

    List<RecipeEntity> apiListToDomainList(List<ApiRecipe> apiRecipe);


    RecipeUI domainToUI(RecipeEntity recipeEntity);

    List<RecipeUI> domainListToUI(List<RecipeEntity> recipeEntityList);
}
