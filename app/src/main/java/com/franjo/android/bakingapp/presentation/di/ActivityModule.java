package com.franjo.android.bakingapp.presentation.di;

import com.franjo.android.bakingapp.presentation.BaseActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector(modules = {FragmentModule.class})
    abstract BaseActivity contributesBaseActivity();
}
