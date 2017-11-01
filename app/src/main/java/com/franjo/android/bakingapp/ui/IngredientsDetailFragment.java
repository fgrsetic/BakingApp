package com.franjo.android.bakingapp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.franjo.android.bakingapp.R;

/**
 * Created by Franjo on 1.11.2017..
 */

public class IngredientsDetailFragment extends Fragment {

    public IngredientsDetailFragment() {

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_recipe_detail, container, false);
    }
}

