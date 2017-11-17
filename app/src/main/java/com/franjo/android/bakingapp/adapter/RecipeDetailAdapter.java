package com.franjo.android.bakingapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.franjo.android.bakingapp.OnStepItemClickListener;
import com.franjo.android.bakingapp.R;
import com.franjo.android.bakingapp.model.Steps;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Franjo on 7.11.2017..
 */

public class RecipeDetailAdapter extends RecyclerView.Adapter<RecipeDetailAdapter.ViewHolder> {

    private List<Steps> mListSteps = new ArrayList<>();
    private LayoutInflater mInflater;
    private OnStepItemClickListener mlistener;


    public RecipeDetailAdapter(Context context, List<Steps> stepsList) {
        mListSteps = stepsList;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        int layoutForListItem = R.layout.recipe_detailed_cardview_item;

        View rootView = mInflater.inflate(layoutForListItem, parent, false);

        return new ViewHolder(rootView, mListSteps);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Steps data = mListSteps.get(position);

        holder.tvBullet.setText("\u2022");

        String shortDescriptiom = data.getShortDescription();
        holder.tvShortDescription.setText(shortDescriptiom);

        holder.tvStepsDetails.setText("\u25BA");


    }

    @Override
    public int getItemCount() {
        return (mListSteps == null) ? 0 : mListSteps.size();
    }

    public void setOnStepFragmentClickListener(OnStepItemClickListener listener) {
        mlistener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private List<Steps> mListSteps;

        @BindView(R.id.tvBullet)
        TextView tvBullet;
        @BindView(R.id.tvShortDescription)
        TextView tvShortDescription;
        @BindView(R.id.tvStepsDetails)
        TextView tvStepsDetails;

        ViewHolder(View itemView, List<Steps> listSteps) {
            super(itemView);
            mListSteps = listSteps;

            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mlistener != null) {
                mlistener.itemListClicked(mListSteps, getAdapterPosition());
            }
        }
    }
}
