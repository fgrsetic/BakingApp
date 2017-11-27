package com.franjo.android.bakingapp.ui;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.franjo.android.bakingapp.IdlingResource.SimpleIdlingResource;
import com.franjo.android.bakingapp.OnRecipesItemClickListener;
import com.franjo.android.bakingapp.R;
import com.franjo.android.bakingapp.adapter.RecipeMainAdapter;
import com.franjo.android.bakingapp.model.Recipes;
import com.franjo.android.bakingapp.network.RecipeAPIClient;
import com.franjo.android.bakingapp.network.RecipeAPIInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Franjo on 15.11.2017..
 */

public class RecipeMainFragment extends Fragment {

    private RecipeMainAdapter mAdapter;

    // A reference to the RecyclerView in the fragment_recipe_master_list xml layout file
    @BindView(R.id.recipe_recycler_view)
    RecyclerView recyclerView;

    List<Recipes> mListRecipes;

    OnRecipesItemClickListener mListener;

    // Override onAttach to make sure that the container activity has implemented the callback
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the host activity has implemented the callback interface
        // If not, it throws an exception
        try {
            mListener = (OnRecipesItemClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnRecipesItemClickListener");
        }
    }

    public RecipeMainFragment() {

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_main_recipe, container, false);

        ButterKnife.bind(this, fragmentView);

        mListRecipes = new ArrayList<>();
        // Create the adapter
        // This adapter takes in the context and an ArrayList of the image view and textview to display
        mAdapter = new RecipeMainAdapter(getContext(), mListRecipes);

        // Allows recyclerview optimization on UI,
        // allowing avoid invalidating whole layout when adapter contents change
        recyclerView.setHasFixedSize(true);

        // Set the adapter on recyclerview
        recyclerView.setAdapter(mAdapter);

        mAdapter.setItemClickedListener(mListener);

        // LayoutManager determines how collection of items is displayed in a grid
        // Displaying different column count depending on orientation
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        }

        /**
         * The IdlingResource is null in production as set by the @Nullable annotation which means
         * the value is allowed to be null.
         *
         * If the idle state is true, Espresso can perform the next action.
         * If the idle state is false, Espresso will wait until it is true before
         * performing the next action.
         */
        final SimpleIdlingResource idlingResource = (SimpleIdlingResource)((RecipeMainActivity)getActivity()).getIdlingResource();
        idlingResource.setIdleState(false);


        final RecipeAPIInterface recipeAPI = RecipeAPIClient.getRecipes();
        Call<List<Recipes>> call = recipeAPI.getRecipes();

            call.enqueue(new Callback<List<Recipes>>() {
            @Override
            public void onResponse(@NonNull Call<List<Recipes>> call, @NonNull Response<List<Recipes>> response) {

                if(response.isSuccessful()) {
                    List<Recipes> recipesList = response.body();

                    mAdapter.addRecipes(recipesList);

                    idlingResource.setIdleState(true);

                } else {
                    Toast.makeText(getContext(), (CharSequence) response.errorBody(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Recipes>> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });

        return fragmentView;

    }


}
