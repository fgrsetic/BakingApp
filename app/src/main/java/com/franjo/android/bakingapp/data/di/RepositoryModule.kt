package com.franjo.android.bakingapp.data.di

import com.franjo.android.bakingapp.data.repository.RecipeRepositoryImpl
import com.franjo.android.bakingapp.domain.repository.RecipeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindRecipeRepository(
        impl: RecipeRepositoryImpl
    ): RecipeRepository

}
