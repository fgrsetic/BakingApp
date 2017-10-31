package com.franjo.android.bakingapp.service;

import com.franjo.android.bakingapp.model.Recipes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Franjo on 30.10.2017..
 */

public interface RecipeAPI {

    @GET("baking.json")
    Call<List<Recipes>> getRecipes();
}
