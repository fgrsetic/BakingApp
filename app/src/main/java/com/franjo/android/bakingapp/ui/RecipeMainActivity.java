package com.franjo.android.bakingapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;

import com.franjo.android.bakingapp.OnRecipesItemClickListener;
import com.franjo.android.bakingapp.R;
import com.franjo.android.bakingapp.model.Recipes;
import com.franjo.android.bakingapp.utilities.Constants;

import java.util.ArrayList;
import java.util.List;

// This activity is responsible for displaying the master list of all recipes
public class RecipeMainActivity extends AppCompatActivity implements OnRecipesItemClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

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


