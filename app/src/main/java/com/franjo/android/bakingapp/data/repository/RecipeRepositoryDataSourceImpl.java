package com.franjo.android.bakingapp.data.repository;

import com.franjo.android.bakingapp.utilities.mapper.IModelMapper;
import com.franjo.android.bakingapp.data.network.RecipeApiService;
import com.franjo.android.bakingapp.domain.model.RecipeEntity;
import com.franjo.android.bakingapp.domain.repository.IRecipeRepository;
import com.franjo.android.bakingapp.utilities.schedulers.SchedulerProvider;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;


public class RecipeRepositoryDataSourceImpl implements IRecipeRepository {

    private RecipeApiService recipeApiService;
    private IModelMapper modelMapper;

    @Inject
    public RecipeRepositoryDataSourceImpl(RecipeApiService recipeApiService, IModelMapper modelMapper) {
        this.recipeApiService = recipeApiService;
        this.modelMapper = modelMapper;
    }

    @Override
    public Single<List<RecipeEntity>> getAllRecipes() {
        // defer operator does not create the Observable until the observer subscribes,
        // and create a fresh Observable for each observer
        return Single.defer(recipeApiService::getAllRecipes)
                .subscribeOn(new SchedulerProvider().getIOScheduler())
                .map(apiRecipes -> modelMapper.apiListToDomainList(apiRecipes));
    }

}
