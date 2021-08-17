package com.franjo.android.bakingapp.presentation.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.franjo.android.bakingapp.R
import com.franjo.android.bakingapp.databinding.FragmentDetailsBinding
import com.franjo.android.bakingapp.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {

    override val fragmentView: Int = R.layout.fragment_details

    private val viewModel: DetailsViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel

    }

}