package com.franjo.android.bakingapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.franjo.android.bakingapp.R;
import com.franjo.android.bakingapp.model.Ingredients;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Franjo on 2.11.2017..
 */

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientsViewHolder> {

    private Context mContext;
    private List<Ingredients> mListIngredients = new ArrayList<>();;
    private LayoutInflater mInflater;


    public IngredientAdapter(Context context, List<Ingredients> ingredientsList) {
        mContext = context;
        mListIngredients = ingredientsList;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public IngredientsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        int layoutForListItem = R.layout.fragment_ingredients_item;

        View rootView = mInflater.inflate(layoutForListItem, parent, false);

        return new IngredientsViewHolder(rootView, mListIngredients);
    }

    @Override
    public void onBindViewHolder(IngredientsViewHolder holder, int position) {
        Ingredients data = mListIngredients.get(position);

        Double quantity = data.getQuantitiy();
        String measure = data.getMeasure();
        String ingredient = data.getIngredient();

        holder.tvQuantity.setText(quantity + "");
        holder.tvMeasure.setText(measure);
        holder.tvIngredient.setText(ingredient);


    }

    @Override
    public int getItemCount() {
        return (mListIngredients == null) ? 0 : mListIngredients.size();
    }



    class IngredientsViewHolder extends RecyclerView.ViewHolder {

        private List<Ingredients> mIngredientsList;

        @BindView(R.id.quantity)
        TextView tvQuantity;
        @BindView(R.id.measure)
        TextView tvMeasure;
        @BindView(R.id.ingredient)
        TextView tvIngredient;


        IngredientsViewHolder(View itemView, List<Ingredients> ingredientsList) {
            super(itemView);
            mIngredientsList = ingredientsList;


            ButterKnife.bind(this, itemView);
        }
    }
}
