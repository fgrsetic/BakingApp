package com.franjo.android.bakingapp.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.franjo.android.bakingapp.ui.IngredientFragment;
import com.franjo.android.bakingapp.ui.StepsFragment;

/**
 * Created by Franjo on 3.11.2017..
 */

public class RecipePagerAdapter extends FragmentPagerAdapter {

    private Context mContext;
    private String tabTitles[] = new String [] {"Ingredients", "Steps"};
    private Bundle mBundle;


    public RecipePagerAdapter(FragmentManager fm, Context context, Bundle data) {
        super(fm);
        mContext = context;
        mBundle = data;

    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }



    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                IngredientFragment ingredientFragment = new IngredientFragment();
                ingredientFragment.setArguments(mBundle);
                return ingredientFragment;

            case 1:
                StepsFragment stepsFragment = new StepsFragment();
                stepsFragment.setArguments(mBundle);
                return stepsFragment;

            default:
                return null;
        }
    }


}
