package com.franjo.android.bakingapp.utilities.mapper;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.franjo.android.bakingapp.data.network.model.ApiRecipe;
import com.franjo.android.bakingapp.domain.model.RecipeEntity;
import com.franjo.android.bakingapp.presentation.model.RecipeUI;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class IModelMapperImpl implements IModelMapper {


    @Override
    public RecipeEntity apiToDomain(final ApiRecipe apiRecipe) {
        return new RecipeEntity(
                apiRecipe.getId(),
                apiRecipe.getName(),
                apiRecipe.getServings(),
                apiRecipe.getImage(),
                apiRecipe.getIngredients(),
                apiRecipe.getSteps());
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public List<RecipeEntity> apiListToDomainList(final List<ApiRecipe> apiRecipes) {
        return Stream.of(apiRecipes)
                .map(new Function<List<ApiRecipe>, RecipeEntity>() {
                    RecipeEntity recipeEntity = null;

                    @Override
                    public RecipeEntity apply(List<ApiRecipe> apiRecipes) {
                        for (ApiRecipe apiRecipe : apiRecipes) {
                            recipeEntity = apiToDomain(apiRecipe);
                        }
                        return recipeEntity;
                    }
                }).collect(toList());

    }

    @Override
    public RecipeUI domainToUI(RecipeEntity recipeEntity) {
        return new RecipeUI(
                recipeEntity.getId(),
                recipeEntity.getName(),
                recipeEntity.getServings(),
                recipeEntity.getImage(),
                recipeEntity.getIngredients(),
                recipeEntity.getSteps());

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public List<RecipeUI> domainListToUI(List<RecipeEntity> recipeEntityList) {
        return Stream.of(recipeEntityList)
                .map(recipeEntities -> {
                    RecipeUI recipeUI = null;
                    for (RecipeEntity recipeEntity : recipeEntityList) {
                        recipeUI = domainToUI(recipeEntity);
                    }
                    return recipeUI;
                }).collect(toList());

    }


}
