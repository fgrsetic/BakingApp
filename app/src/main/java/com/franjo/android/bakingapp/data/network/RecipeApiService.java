package com.franjo.android.bakingapp.data.network;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import static com.franjo.android.bakingapp.data.network.Endpoint.RECIPE_PATH;
import com.franjo.android.bakingapp.data.network.model.ApiRecipe;

import java.util.List;

/**
 * Created by Franjo on 30.10.2017..
 */

public interface RecipeApiService {

    @GET(RECIPE_PATH)
    Single<List<ApiRecipe>> getAllRecipes();
}
