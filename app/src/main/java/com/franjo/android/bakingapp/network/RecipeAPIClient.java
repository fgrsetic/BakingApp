package com.franjo.android.bakingapp.network;

import com.franjo.android.bakingapp.utilities.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Franjo on 30.10.2017..
 */

public class RecipeAPIClient {

    public static RecipeAPIInterface getRecipes() {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(RecipeAPIInterface.class);
    }

}
