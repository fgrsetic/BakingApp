package com.franjo.android.bakingapp.presentation.ui.steps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.franjo.android.bakingapp.R
import com.franjo.android.bakingapp.databinding.FragmentStepBinding
import com.franjo.android.bakingapp.presentation.base.BaseFragment

class StepFragment : BaseFragment<FragmentStepBinding>() {

    override val fragmentView: Int = R.layout.fragment_step

    private val viewModel: StepViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_step, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
    }



}