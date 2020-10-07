package com.franjo.android.bakingapp.presentation;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.franjo.android.bakingapp.BR;

public class BaseViewHolder extends RecyclerView.ViewHolder {

    private ViewDataBinding binding;

    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
        binding = DataBindingUtil.getBinding(itemView);
    }

    public void bind(Object item) {
        binding.setVariable(BR.item, item);
        // This is important, because it forces the data binding to execute immediately,
        // which allows the RecyclerView to make the correct view size measurements
        binding.executePendingBindings();
    }
}
