package com.franjo.android.bakingapp.app.di

import android.content.Context
import com.franjo.android.bakingapp.app.BakingApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideContext(app: BakingApp): Context = app
}