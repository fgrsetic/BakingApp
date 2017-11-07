package com.franjo.android.bakingapp.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.franjo.android.bakingapp.R;
import com.franjo.android.bakingapp.adapter.RecipePagerAdapter;
import com.franjo.android.bakingapp.model.Recipes;
import com.franjo.android.bakingapp.utilities.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


// This activity will display a custom Android lists composed of two lists ingredients and steps
public class RecipeDetailActivity extends AppCompatActivity {

    private ArrayList<Recipes> recipesArrayList;
    private String name;

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.sliding_tabs)
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        ButterKnife.bind(this);


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Bundle data = getIntent().getExtras();

        List<Recipes> recipes = null;
        if (data != null) {
            recipes = data.getParcelableArrayList(Constants.CLICKED_RECIPE);
        }


        if (recipes != null) {
            name = recipes.get(0).getName();
        }

        if (actionBar != null) {
            actionBar.setTitle(name);
        }


        RecipePagerAdapter pagerAdapter = new RecipePagerAdapter(getSupportFragmentManager(), this, data);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);



    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);

        }

        return super.onOptionsItemSelected(item);
    }
}
