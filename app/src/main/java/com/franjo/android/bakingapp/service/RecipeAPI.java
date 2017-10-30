package com.franjo.android.bakingapp.service;

import com.franjo.android.bakingapp.model.Recipes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Franjo on 30.10.2017..
 */

public interface RecipeAPI {

    String URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";

    @GET("users/{user}/repos")
    Call<List<Recipes>> getRecipes();
}
