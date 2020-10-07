package com.franjo.android.bakingapp.presentation.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.franjo.android.bakingapp.domain.model.RecipeEntity;
import com.franjo.android.bakingapp.domain.usecase.GetRecipeUseCase;
import com.franjo.android.bakingapp.presentation.model.RecipeUI;
import com.franjo.android.bakingapp.utilities.schedulers.SchedulerProvider;
import com.franjo.android.bakingapp.utilities.mapper.IModelMapper;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;

public class MainViewModel extends ViewModel {

    @Inject
    GetRecipeUseCase getRecipesUseCase;
    @Inject
    SchedulerProvider schedulerProvider;
    @Inject
    IModelMapper modelMapper;

    private CompositeDisposable compositeDisposable;

    private MutableLiveData<List<RecipeUI>> recipes;

    @Inject
    public MainViewModel() {
        this.compositeDisposable = new CompositeDisposable();
    }

    public LiveData<List<RecipeUI>> getRecipes() {
        if (recipes == null) {
            recipes = new MutableLiveData<>();
            loadRecipes();
        }
        return recipes;
    }

    private void loadRecipes() {
        compositeDisposable.add(getRecipesUseCase.execute()
                .subscribeOn(schedulerProvider.getIOScheduler())
                .observeOn(schedulerProvider.getUIScheduler())
                .subscribeWith(new DisposableSingleObserver<List<RecipeEntity>>() {
                    @Override
                    public void onSuccess(@NonNull List<RecipeEntity> recipeEntities) {
                        recipes.postValue(modelMapper.domainListToUI(recipeEntities));
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                })
        );

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
