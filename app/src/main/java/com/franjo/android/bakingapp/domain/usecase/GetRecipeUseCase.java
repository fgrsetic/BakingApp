package com.franjo.android.bakingapp.domain.usecase;

import com.franjo.android.bakingapp.domain.model.RecipeEntity;
import com.franjo.android.bakingapp.domain.repository.IRecipeRepository;
import com.franjo.android.bakingapp.domain.usecase.base.SingleUseCase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;


public class GetRecipeUseCase implements SingleUseCase<List<RecipeEntity>> {

    private IRecipeRepository repository;

    @Inject
    public GetRecipeUseCase(IRecipeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Single<List<RecipeEntity>> execute() {
        return repository.getAllRecipes();
    }
}
