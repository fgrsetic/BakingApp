package com.franjo.android.bakingapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.franjo.android.bakingapp.R;
import com.franjo.android.bakingapp.model.Recipes;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Franjo on 30.10.2017..
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<Recipes> mListRecipes = new ArrayList<>();

    public RecipeAdapter(Context context, List<Recipes> recipesList) {
        mContext = context;
        mListRecipes = recipesList;
        mInflater = LayoutInflater.from(context);

    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutForCardView = R.layout.recipe_cardview_item;

        View rowView = mInflater.inflate(layoutForCardView, parent, false);

        return new RecipeViewHolder(rowView, mContext, mListRecipes);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {

        String imageRecipe =


    }

    @Override
    public int getItemCount() {
        return (mListRecipes == null) ? 0 : mListRecipes.size();
    }

    class RecipeViewHolder extends RecyclerView.ViewHolder {

        private Context mContext;
        private List<Recipes> mRecipesList;

        @BindView(R.id.recipe_title)
        TextView mTvTitle;

        @BindView(R.id.recipe_image)
        ImageView mImageRecipe;


        RecipeViewHolder(View itemView, Context context, List<Recipes> recipesList) {
            super(itemView);
            mContext = context;
            mRecipesList = recipesList;

        }


    }

}