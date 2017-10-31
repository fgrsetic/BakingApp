package com.franjo.android.bakingapp.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.franjo.android.bakingapp.R;
import com.franjo.android.bakingapp.adapter.RecipeAdapter;
import com.franjo.android.bakingapp.model.Recipes;
import com.franjo.android.bakingapp.service.Controller;
import com.franjo.android.bakingapp.service.RecipeAPI;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Franjo on 30.10.2017..
 */

public class RecipeMainFragment extends Fragment {

    private List<Recipes> mListRecipes;
    private RecipeAdapter mAdapter;

    // A reference to the RecyclerView in the fragment_recipe_master_list xml layout file
    @BindView(R.id.fragment_recipe_recycler_view)
    RecyclerView recyclerView;

    // Mandatory constructor for instantiating the fragment
    public RecipeMainFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_recipe_master_list, container, false);

        ButterKnife.bind(this, rootView);

        // Create the adapter
        // This adapter takes in the context and an ArrayList of the image view and textview to display
        mAdapter = new RecipeAdapter(getContext(), mListRecipes);

        // Allows recyclerview optimization on UI,
        // allowing avoid invalidating whole layout when adapter contents change
        recyclerView.setHasFixedSize(true);

        // Set the adapter on recyclerview
        recyclerView.setAdapter(mAdapter);

        // LayoutManager determines how collection of items is displayed in a grid
        // Displaying different column count depending on orientation
        if (getContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        }

        RecipeAPI recipeAPI = Controller.getRecipes();
        Call<List<Recipes>> call = recipeAPI.getRecipes();

        call.enqueue(new Callback<List<Recipes>>() {
            @Override
            public void onResponse(@NonNull Call<List<Recipes>> call, @NonNull Response<List<Recipes>> response) {

                if(response.isSuccessful()) {
                    List<Recipes> recipesList = response.body();
                    mAdapter.addRecipes(recipesList);

                } else {
                    Toast.makeText(getContext(), (CharSequence) response.errorBody(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Recipes>> call, Throwable t) {
                t.printStackTrace();
            }
        });

        // Return the root view
        return rootView;
    }
}
