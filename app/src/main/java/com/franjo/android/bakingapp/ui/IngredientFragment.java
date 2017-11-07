package com.franjo.android.bakingapp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.franjo.android.bakingapp.R;
import com.franjo.android.bakingapp.adapter.IngredientAdapter;
import com.franjo.android.bakingapp.model.Ingredients;
import com.franjo.android.bakingapp.model.Recipes;
import com.franjo.android.bakingapp.utilities.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Franjo on 1.11.2017..
 */

public class IngredientFragment extends Fragment {

    private static final String TAG = IngredientFragment.class.getSimpleName();

    private IngredientAdapter mAdapter;

    // A reference to the RecyclerView in the fragment_recipe_master_list xml layout file
    @BindView(R.id.ingredient_layout)
    RecyclerView recyclerView;

    List<Recipes> mRecipesList;
    List<Ingredients> mIngredientsList;

    List<Ingredients> mTempList;
    private int mListIndex;

    public IngredientFragment() {

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_ingredient_layout, container, false);

        ButterKnife.bind(this, rootView);

        mRecipesList = new ArrayList<>();

        Bundle bundle = getArguments();

        mRecipesList = bundle.getParcelableArrayList(Constants.CLICKED_RECIPE);

        if (mRecipesList != null) {
            mIngredientsList = mRecipesList.get(mListIndex).getIngredients();

        } else {
            Log.v(TAG, "This fragment has a null list of ingredients id's");

        }

        mAdapter = new IngredientAdapter(getActivity(), mIngredientsList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        return rootView;
        }

//        public void setListIds(List<Recipes> recipesIds) {
//            mRecipesList = recipesIds;
//        }
//
//        public void setListIndex() {
//            mListIndex = 0;
//        }

    }

