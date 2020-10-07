package com.franjo.android.bakingapp.presentation.ui.main;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.franjo.android.bakingapp.R;
import com.franjo.android.bakingapp.databinding.FragmentMainBinding;
import com.franjo.android.bakingapp.presentation.BaseFragment;

import dagger.android.support.AndroidSupportInjection;


public class MainRecipeFragment extends BaseFragment<FragmentMainBinding> {

    @Override
    public int getFragmentView() {
        return R.layout.fragment_main;
    }

    private MainViewModel mViewModel;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        binding.setViewModel(mViewModel);

        setAdapter();
    }

    private void setAdapter() {
        MainRecipeAdapter adapter = new MainRecipeAdapter();
        binding.rvRecipeMain.setAdapter(adapter);
    }

}