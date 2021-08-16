package com.franjo.android.bakingapp.utilities

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.franjo.android.bakingapp.R
import com.franjo.android.bakingapp.presentation.model.RecipeUI
import com.franjo.android.bakingapp.presentation.ui.recipe.RecipeAdapter

@BindingAdapter("recipeList")
fun bindRecipeList(recyclerView: RecyclerView, list: List<RecipeUI>?) {

    if (list != null) {
        val adapter: RecipeAdapter = recyclerView.adapter as RecipeAdapter
        adapter.submitList(list)
    }
}

// Binding adapter used to display images from URL using Glide
@BindingAdapter("thumbnailPath")
fun setThumbnailUrl(imageView: ImageView, list: List<RecipeUI>) {
    val url: String = list.forEach { recipeUi ->
        recipeUi.steps.map {
            it.thumbnailURL
        }
    }.toString()
    Glide.with(imageView.context)
        .load(url)
        .apply(
            RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image)
        )
        .into(imageView)
}

// Binding adapter to show the status in the ImageView and show/hide the view
@BindingAdapter("loadingStatus", "errorStatus")
fun bindStatus(progressBar: ProgressBar, isLoading: Boolean, errorImage: ImageView) {
    if (isLoading) {
        progressBar.visibility = View.VISIBLE
        errorImage.visibility = View.GONE
    } else {
        progressBar.visibility = View.GONE
        errorImage.visibility = View.VISIBLE
        errorImage.setImageResource(R.drawable.ic_connection_error)
    }
}