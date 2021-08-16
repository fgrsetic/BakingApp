package com.franjo.android.bakingapp.presentation.ui.recipe

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.franjo.android.bakingapp.R
import com.franjo.android.bakingapp.databinding.FragmentRecipeBinding
import com.franjo.android.bakingapp.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeFragment : BaseFragment<FragmentRecipeBinding>() {

    override val fragmentView: Int
        get() = R.layout.fragment_recipe

    private val viewModel: RecipeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        viewModel.setStateEvent(RecipeViewEvent.GetRecipesEvent)
    }
}