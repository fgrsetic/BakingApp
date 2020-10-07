package com.franjo.android.bakingapp.presentation.di;

import com.franjo.android.bakingapp.presentation.ui.main.MainRecipeFragment;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import kotlin.annotation.MustBeDocumented;

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    @FragmentScope
    abstract MainRecipeFragment contributeMainRecipeFragment();

    @Scope
    @MustBeDocumented
    @Retention(RetentionPolicy.RUNTIME)
    public @interface FragmentScope{}
}
