package com.franjo.android.bakingapp;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.franjo.android.bakingapp.adapter.RecipeAdapter;
import com.franjo.android.bakingapp.model.Recipes;

import java.util.List;

/**
 * Created by Franjo on 30.10.2017..
 */

public class RecipeMainFragment extends Fragment {

    private List<Recipes> mListRecipes;

    // Mandatory constructor for instantiating the fragment
    public RecipeMainFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_recipe_master_list, container, false);

        // Get a reference to the RecyclerView in the fragment_master_list xml layout file
        RecyclerView recyclerView = rootView.findViewById(R.id.fragment_recipe_recycler_view);

        // Create the adapter
        // This adapter takes in the context and an ArrayList of the image view and textview to display
        RecipeAdapter adapter = new RecipeAdapter(getContext(), mListRecipes);

        // LayoutManager determines how collection of items is displayed in a grid
        // Displaying different column count depending on orientation
        if (getContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        } else {
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }

        // Set the adapter on recyclerview
        recyclerView.setAdapter(adapter);

        // Return the root view
        return rootView;
    }
}
