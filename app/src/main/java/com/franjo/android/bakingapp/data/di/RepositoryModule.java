package com.franjo.android.bakingapp.data.di;

import com.franjo.android.bakingapp.data.network.RecipeApiService;
import com.franjo.android.bakingapp.data.repository.RecipeRepositoryDataSourceImpl;
import com.franjo.android.bakingapp.domain.repository.IRecipeRepository;
import com.franjo.android.bakingapp.utilities.mapper.IModelMapper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class RepositoryModule {

    @Provides
    @Singleton
    IRecipeRepository provideRepositoryImpl(
            RecipeApiService recipeApiService, IModelMapper modelMapper) {
        return new RecipeRepositoryDataSourceImpl(recipeApiService, modelMapper);
    }
}
