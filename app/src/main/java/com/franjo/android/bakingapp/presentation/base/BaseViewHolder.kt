package com.franjo.android.bakingapp.presentation.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.franjo.android.bakingapp.BR

// ViewDataBinding, the base class for all generated bindings,
// instead of the specific ItemBinding
class BaseViewHolder(private val binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Any?) {
        binding.setVariable(BR.item, item)
        // This is important, because it forces the data binding to execute immediately,
        // which allows the RecyclerView to make the correct view size measurements
        binding.executePendingBindings()
    }

}