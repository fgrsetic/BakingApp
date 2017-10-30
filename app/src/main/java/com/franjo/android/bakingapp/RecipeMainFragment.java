package com.franjo.android.bakingapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Franjo on 30.10.2017..
 */

public class RecipeMainFragment extends Fragment {



    // Mandatory constructor for instantiating the fragment
    public RecipeMainFragment() {

    }

    /**
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fargment_recipe_main, container, false);

        return rootView;
    }
}
