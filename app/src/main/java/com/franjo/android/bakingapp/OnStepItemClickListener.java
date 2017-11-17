package com.franjo.android.bakingapp;

import com.franjo.android.bakingapp.model.Steps;

import java.util.List;

/**
 * Created by Franjo on 8.11.2017..
 */

public interface OnStepItemClickListener {
    void itemListClicked(List<Steps> listSteps, int position);
}
