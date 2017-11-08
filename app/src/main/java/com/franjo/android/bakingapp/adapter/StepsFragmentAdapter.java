package com.franjo.android.bakingapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.franjo.android.bakingapp.R;
import com.franjo.android.bakingapp.model.Steps;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Franjo on 7.11.2017..
 */

public class StepsFragmentAdapter extends RecyclerView.Adapter<StepsFragmentAdapter.StepsViewHolder> {

    private Context mContext;
    private List<Steps> mListSteps = new ArrayList<>();
    private LayoutInflater mInflater;


    public StepsFragmentAdapter(Context context, List<Steps> stepsList) {
        mContext = context;
        mListSteps = stepsList;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public StepsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        int layoutForListItem = R.layout.fragment_steps_item;

        View rootView = mInflater.inflate(layoutForListItem, parent, false);

        return new StepsViewHolder(rootView, mListSteps);
    }

    @Override
    public void onBindViewHolder(StepsViewHolder holder, int position) {

        Steps data = mListSteps.get(position);

        String shortDescriptiom = data.getShortDescription();
        holder.tvShortDescription.setText(shortDescriptiom);


    }

    @Override
    public int getItemCount() {
        return (mListSteps == null) ? 0 : mListSteps.size();
    }

    class StepsViewHolder extends RecyclerView.ViewHolder {

        private List<Steps> mListSteps;

        @BindView(R.id.tvShortDescription)
        TextView tvShortDescription;

        StepsViewHolder(View itemView, List<Steps> listSteps) {
            super(itemView);
            mListSteps = listSteps;

            ButterKnife.bind(this, itemView);
        }
    }
}
