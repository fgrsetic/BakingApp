package com.franjo.android.bakingapp.ui;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.franjo.android.bakingapp.ItemClickListener;
import com.franjo.android.bakingapp.R;
import com.franjo.android.bakingapp.adapter.RecipeMainAdapter;
import com.franjo.android.bakingapp.model.Recipes;
import com.franjo.android.bakingapp.service.Controller;
import com.franjo.android.bakingapp.service.RecipeAPI;
import com.franjo.android.bakingapp.utilities.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Franjo on 6.11.2017..
 */

public class MasterListFragment extends Fragment implements ItemClickListener{

    private RecipeMainAdapter mAdapter;
    private List<Recipes> mListRecipes;

    // A reference to the RecyclerView in the fragment_recipe_master_list xml layout file
    @BindView(R.id.recipe_recycler_view)
    RecyclerView recyclerView;

    // Mandatory empty constructor
    public MasterListFragment() {

    }

    // Inflates the RecyclerView of all recipes
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_master_list, container, false);

        ButterKnife.bind(this, rootView);

        mListRecipes = new ArrayList<>();
        // Create the adapter
        // This adapter takes in the context and an ArrayList of the image view and textview to display
        mAdapter = new RecipeMainAdapter(getContext(), mListRecipes);

        // Allows recyclerview optimization on UI,
        // allowing avoid invalidating whole layout when adapter contents change
        recyclerView.setHasFixedSize(true);

        // Set the adapter on recyclerview
        recyclerView.setAdapter(mAdapter);

        mAdapter.setItemClickedListener(this);

        // LayoutManager determines how collection of items is displayed in a grid
        // Displaying different column count depending on orientation
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        }

        final RecipeAPI recipeAPI = Controller.getRecipes();
        Call<List<Recipes>> call = recipeAPI.getRecipes();

        call.enqueue(new Callback<List<Recipes>>() {
            @Override
            public void onResponse(@NonNull Call<List<Recipes>> call, @NonNull Response<List<Recipes>> response) {

                if(response.isSuccessful()) {
                    List<Recipes> recipesList = response.body();

//                    Bundle recipeBundle = new Bundle();
//                    recipeBundle.putParcelableArrayList("All recipes", (ArrayList<? extends Parcelable>) recipesList);


                    mAdapter.addRecipes(recipesList);

                } else {
                    Toast.makeText(getContext(), (CharSequence) response.errorBody(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Recipes>> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });

        return rootView;
    }

    @Override
    public void itemClicked(Recipes position) {

        Bundle bundle = new Bundle();
        mListRecipes = new ArrayList<>();
        mListRecipes.add(position);
        bundle.putParcelableArrayList(Constants.CLICKED_RECIPE, (ArrayList<? extends Parcelable>) mListRecipes);

        final Intent intent = new Intent(getContext(), RecipeDetailActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);


    }

}
