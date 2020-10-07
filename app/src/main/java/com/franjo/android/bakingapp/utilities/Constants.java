package com.franjo.android.bakingapp.utilities;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by Franjo on 30.10.2017..
 */

public final class Constants {

    public static final String CLICKED_RECIPE = "clicked_recipe"; // Clicked viewcard item in RecipeMainActivity
    public static final String CLICKED_STEP = "clicked_step"; // Clicked step short description list item
    public static final String INDEX = "step_index";

    public static final String STEP_DETAILS_STACK = "step_details_stack";
    public static final String RECIPE_DETAILS_STACK = "recipe_details_stack";

    // Widget constants
    public static final String ACTION_APPWIDGET_UPDATE = "android.appwidget.action.APPWIDGET_UPDATE"; // Action task that IntentService performs
    public static final String EXTRA_INGREDIENT_LIST = "com.franjo.android.bakingapp.extra.ingredient_list";



    // Method for formatting quantity with fractionDigit method
    public static String format(Number n) {
        NumberFormat format = DecimalFormat.getInstance();
        format.setRoundingMode(RoundingMode.FLOOR);
        format.setMinimumFractionDigits(0);
        format.setMaximumFractionDigits(2);
        return format.format(n);
    }


}

