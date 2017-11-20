package com.franjo.android.bakingapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.franjo.android.bakingapp.OnStepItemClickListener;
import com.franjo.android.bakingapp.R;
import com.franjo.android.bakingapp.adapter.RecipeDetailAdapter;
import com.franjo.android.bakingapp.model.Recipes;
import com.franjo.android.bakingapp.model.Steps;
import com.franjo.android.bakingapp.utilities.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Franjo on 10.11.2017..
 */

public class RecipeDetailFragment extends Fragment implements OnStepItemClickListener {

    private static final String TAG = RecipeDetailFragment.class.getSimpleName();

    @BindView(R.id.fragment_detail_layout)
    RecyclerView recyclerView;

    @BindView(R.id.tvIngredientDetails)
    TextView tvIngredientDetails;

    @BindView(R.id.tvIngredientTitle)
    TextView tvIngredientTitle;

    @BindView(R.id.tvStepsTitle)
    TextView tvStepTitle;

    List<Recipes> mListRecipes;
    List<Steps> mListSteps;

    // Variables to store the index of the recipe that this fragment displays
    private int mListIndex;
    Bundle bundle;

    // Define a new interface OnStepDetailListener that triggers a callback in the host activity
    OnStepDetailListener mCallback;


    // Mandatory empty constructor
    public RecipeDetailFragment() {

    }

    // OnStepDetailListener interface, calls a method in the host activity named onStepSelected
    public interface OnStepDetailListener {
        void onStepSelected(List<Steps> stepsList, int position);
    }



    // Override onAttach to make sure that the container activity has implemented the callback
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the host activity has implemented the callback interface
        // If not, it throws an exception
        try {
            mCallback = (OnStepDetailListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnStepDetailListener");
        }
    }


    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_recipes_detail_layout, container, false);
        ButterKnife.bind(this, fragmentView);

        tvIngredientTitle.setText(R.string.ingredients);
        tvIngredientDetails.setText("\u25BA");
        tvStepTitle.setText(R.string.steps);

        final Bundle bundle = getArguments();
        mListRecipes = bundle.getParcelableArrayList(Constants.CLICKED_RECIPE);

        if (mListRecipes != null) {
            mListSteps = mListRecipes.get(mListIndex).getSteps();

        } else {
            Log.v(TAG, "This fragment has a null list of steps id's");

        }

        tvIngredientTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                IngredientFragment ingredientFragment = new IngredientFragment();
                ingredientFragment.setArguments(bundle);
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, ingredientFragment)
                        .addToBackStack(null)
                        .commit();

            }

        });

        RecipeDetailAdapter mAdapter = new RecipeDetailAdapter(getActivity(), mListSteps);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter.setOnStepFragmentClickListener(this);

        return fragmentView;
    }


    @Override
    public void itemListClicked(List<Steps> stepsList, int position) {
        mCallback.onStepSelected(stepsList, position);

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(Constants.CLICKED_RECIPE, (ArrayList<? extends Parcelable>) mListRecipes);
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            savedInstanceState.getParcelableArrayList(Constants.CLICKED_RECIPE);
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }
}
