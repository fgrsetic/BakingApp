package com.franjo.android.bakingapp.presentation.ui.main;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.franjo.android.bakingapp.R;
import com.franjo.android.bakingapp.presentation.BaseViewHolder;
import com.franjo.android.bakingapp.presentation.model.RecipeUI;

import java.util.List;

public class MainRecipeAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private final AsyncListDiffer<RecipeUI> mDiffer = new AsyncListDiffer<RecipeUI>(this, DIFF_CALLBACK);

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(
                layoutInflater,
                R.layout.item_recipe,
                parent, false
        );
        return new BaseViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        RecipeUI recipeUI = mDiffer.getCurrentList().get(position);
        holder.bind(recipeUI);
    }

    @Override
    public int getItemCount() {
        return mDiffer.getCurrentList().size();
    }

    public void submitList(List<RecipeUI> list) {
        mDiffer.submitList(list);
    }

    public static final DiffUtil.ItemCallback<RecipeUI> DIFF_CALLBACK
            = new DiffUtil.ItemCallback<RecipeUI>() {
        @Override
        public boolean areItemsTheSame(
                @NonNull RecipeUI oldUser, @NonNull RecipeUI newUser) {
            // User properties may have changed if reloaded from the DB, but ID is fixed
            return oldUser.getId().equals(newUser.getId());
        }

        @Override
        public boolean areContentsTheSame(
                @NonNull RecipeUI oldUser, @NonNull RecipeUI newUser) {
            // NOTE: if you use equals, your object must properly override Object#equals()
            // Incorrectly returning false here will result in too many animations.
            return oldUser.equals(newUser);
        }
    };
}