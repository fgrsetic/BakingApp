package com.franjo.android.bakingapp.presentation.ui.recipe

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.franjo.android.bakingapp.R
import com.franjo.android.bakingapp.presentation.base.BaseViewHolder
import com.franjo.android.bakingapp.presentation.model.RecipeUI


class RecipeAdapter : RecyclerView.Adapter<BaseViewHolder>() {

    private val mDiffer = AsyncListDiffer(this, DIFF_CALLBACK)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            layoutInflater,
            R.layout.item_recipe,
            parent, false
        )
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val recipeUI = mDiffer.currentList[position]
        holder.bind(recipeUI)
    }

    override fun getItemCount(): Int {
        return mDiffer.currentList.size
    }

    fun submitList(list: List<RecipeUI>?) {
        mDiffer.submitList(list)
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<RecipeUI> =
            object : DiffUtil.ItemCallback<RecipeUI>() {
                override fun areItemsTheSame(
                    oldUser: RecipeUI, newUser: RecipeUI
                ): Boolean {
                    // User properties may have changed if reloaded from the DB, but ID is fixed
                    return oldUser.id == newUser.id
                }

                override fun areContentsTheSame(
                    oldUser: RecipeUI, newUser: RecipeUI
                ): Boolean {
                    // NOTE: if you use equals, your object must properly override Object#equals()
                    // Incorrectly returning false here will result in too many animations.
                    return oldUser == newUser
                }
            }
    }
}