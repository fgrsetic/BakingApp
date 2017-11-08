package com.franjo.android.bakingapp.ui;

import android.content.Context;
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

import com.franjo.android.bakingapp.OnFragmentItemClickListener;
import com.franjo.android.bakingapp.R;
import com.franjo.android.bakingapp.adapter.StepsFragmentAdapter;
import com.franjo.android.bakingapp.model.Recipes;
import com.franjo.android.bakingapp.model.Steps;
import com.franjo.android.bakingapp.utilities.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Franjo on 8.11.2017..
 */

public class StepsFragment extends Fragment implements OnFragmentItemClickListener {

    private static final String TAG = IngredientFragment.class.getSimpleName();

    private StepsFragmentAdapter mAdapter;

    // A reference to the RecyclerView in the fragment_recipe_master_list xml layout file
    @BindView(R.id.steps_layout)
    RecyclerView recyclerView;

    List<Recipes> mRecipesList;
    List<Steps> mStepslist;

    private int mListIndex;

    // Interface OnImageClickListener that triggers a callback in the container activity
    OnFragmentItemClickListener mCallback;

    // Overriding onAttach make sure that the container activity has implemented the callback
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the host activity has implemented the callback interface
        // If not, it throws an exception
        try {
            mCallback = (OnFragmentItemClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnImageClickListener");
        }
    }


    // Mandatory empty constructor
    public StepsFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View fragmentView = inflater.inflate(R.layout.fragment_steps_layout, container, false);

        ButterKnife.bind(this, fragmentView);

        mRecipesList = new ArrayList<>();

        Bundle bundle = getArguments();

        mRecipesList = bundle.getParcelableArrayList(Constants.CLICKED_RECIPE);

        if (mRecipesList != null) {
            mStepslist = mRecipesList.get(mListIndex).getSteps();

        } else {
            Log.v(TAG, "This fragment has a null list of steps id's");

        }

        mAdapter = new StepsFragmentAdapter(getActivity(), mStepslist);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        return fragmentView;
    }

    public void setListIds(List<Recipes> recipesIds) {
        mRecipesList = recipesIds;
    }

    public void setListIndex() {
        mListIndex = 0;
    }

    @Override
    public void itemListClicked(Steps position) {
        Bundle bundle = new Bundle();
        mStepslist = new ArrayList<>();
        mStepslist.add(position);
        bundle.putParcelableArrayList(Constants.CLICKED_STEP, (ArrayList<? extends Parcelable>) mStepslist);


    }
}


