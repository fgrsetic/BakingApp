package com.franjo.android.bakingapp.ui;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.franjo.android.bakingapp.R;
import com.franjo.android.bakingapp.model.Recipes;
import com.franjo.android.bakingapp.model.Steps;
import com.franjo.android.bakingapp.utilities.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


// This activity will display a custom Android lists composed of two lists ingredients and steps
public class RecipeDetailActivity extends AppCompatActivity implements RecipeDetailFragment.OnStepDetailListener, StepDetailFragment.OnStepDetailListener {

    private static final String TAG = RecipeDetailActivity.class.getSimpleName();

    public String mRecipeName;
    private FragmentManager fragmentManager;

    @BindView(R.id.layout_fragment_container)
    LinearLayout linearLayout;


    // Track whether to display a two-pane or single-pane UI
    // A single-pane display refers to phone screens, and two-pane to larger tablet screens
    private boolean mTwoPane;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        ButterKnife.bind(this);

        // Determine if you're creating a two-pane or single-pane display
        if (linearLayout != null) {

            // This LinearLayout will only initially exist in the two-pane tablet case
            mTwoPane = true;

            // Only create new fragments when there is no previously saved state
            if (savedInstanceState == null) {

                fragmentManager = getSupportFragmentManager();
                Bundle data = getIntent().getExtras();
                List<Recipes> mRecipes = new ArrayList<>();

                if (data != null) {
                    mRecipes = data.getParcelableArrayList(Constants.CLICKED_RECIPE);
                }
                if (mRecipes != null) {
                    mRecipeName = mRecipes.get(0).getName();
                }

                RecipeDetailFragment recipeDetailFragment = new RecipeDetailFragment();
                recipeDetailFragment.setArguments(data);
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, recipeDetailFragment)
                        .addToBackStack(Constants.RECIPE_DETAILS_STACK)
                        .commit();

                StepDetailFragment stepDetailFragment = new StepDetailFragment();
                stepDetailFragment.setArguments(data);
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_land, stepDetailFragment)
                        .addToBackStack(Constants.STEP_DETAILS_STACK)
                        .commit();

                }


        } else {
            mTwoPane = false;
            mRecipeName = savedInstanceState.getString("Title");

        }

        if (actionBar != null) {
            actionBar.setTitle(mRecipeName);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            if (findViewById(R.id.fragment_container_land) == null) {
                if (fragmentManager.getBackStackEntryCount() > 1) {
                    fragmentManager.popBackStack(Constants.RECIPE_DETAILS_STACK, 0);
                } else if (fragmentManager.getBackStackEntryCount() > 0) {

                    finish();
                }
            } else {
                finish();
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStepSelected(List<Steps> stepsList, int position) {
        StepDetailFragment stepDetailFragment = new StepDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(Constants.CLICKED_STEP, (ArrayList<? extends Parcelable>) stepsList);
        bundle.putInt("Index", position);
        stepDetailFragment.setArguments(bundle);


        if (linearLayout != null)  {
            mTwoPane = true;
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_land, stepDetailFragment)
                    .addToBackStack(Constants.STEP_DETAILS_STACK)
                    .commit();

        } else {
            mTwoPane = false;
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, stepDetailFragment)
                    .addToBackStack(Constants.STEP_DETAILS_STACK)
                    .commit();
        }

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("Title", mRecipeName);


    }

    @Override
    public void onBackPressed() {
         if (fragmentManager.getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else if (fragmentManager.getBackStackEntryCount() > 0) {
            finish();
        }

    }


}
