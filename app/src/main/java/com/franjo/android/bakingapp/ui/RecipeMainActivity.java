package com.franjo.android.bakingapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;

import com.franjo.android.bakingapp.IdlingResource.SimpleIdlingResource;
import com.franjo.android.bakingapp.OnRecipesItemClickListener;
import com.franjo.android.bakingapp.R;
import com.franjo.android.bakingapp.model.Recipes;
import com.franjo.android.bakingapp.utilities.Constants;

import java.util.ArrayList;
import java.util.List;

// This activity is responsible for displaying the list of all recipes
public class RecipeMainActivity extends AppCompatActivity implements OnRecipesItemClickListener {

    // A SimpleIdlingResource variable that will be null in production
    @Nullable
    private SimpleIdlingResource mIdlingResource;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        getIdlingResource();
    }


    // Method that returns the IdlingResource variable.
    // It will instantiate a new instance of SimpleIdlingResource if the IdlingResource is null.
    // This method will only be called from test.
    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (mIdlingResource == null) {
            mIdlingResource = new SimpleIdlingResource();
        }
        return mIdlingResource;
    }



    @Override
    public void itemClicked(Recipes position) {
        Bundle bundle = new Bundle();
        List<Recipes> mListRecipes = new ArrayList<>();
        mListRecipes.add(position);
        bundle.putParcelableArrayList(Constants.CLICKED_RECIPE, (ArrayList<? extends Parcelable>) mListRecipes);

        final Intent intent = new Intent(this, RecipeDetailActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

}


