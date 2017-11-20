package com.franjo.android.bakingapp.ui;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.franjo.android.bakingapp.R;
import com.franjo.android.bakingapp.adapter.IngredientsFragmentAdapter;
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

    @BindView(R.id.ingredient_detail_layout)
    RecyclerView recyclerView;

    private List<Recipes> mRecipesList;
    private List<Ingredients> mIngredientsList;
    private int mListIndex;


    // Mandatory empty constructor
    public IngredientFragment() {

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mRecipesList = new ArrayList<>();
        Bundle bundle = getArguments();

        if(savedInstanceState != null) {
            mRecipesList = savedInstanceState.getParcelableArrayList(Constants.CLICKED_RECIPE);
        } else {
            mRecipesList = bundle.getParcelableArrayList(Constants.CLICKED_RECIPE);
        }

        View rootView = inflater.inflate(R.layout.fragment_ingredient_detail_layout, container, false);

        ButterKnife.bind(this, rootView);

        if (mRecipesList != null) {
            mIngredientsList = mRecipesList.get(mListIndex).getIngredients();

        } else {
            Log.v(TAG, "This fragment has a null list of ingredients id's");

        }

        IngredientsFragmentAdapter mAdapter = new IngredientsFragmentAdapter(getActivity(), mIngredientsList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle currentState) {
        super.onSaveInstanceState(currentState);
        currentState.putParcelableArrayList(Constants.CLICKED_RECIPE, (ArrayList<? extends Parcelable>) mRecipesList);


    }


}

