package com.franjo.android.bakingapp.domain.di

import com.franjo.android.bakingapp.domain.mapper.EntityMapper
import com.franjo.android.bakingapp.domain.mapper.EntityMapperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface EntityMapperModule {

    @Binds
    fun bindsEntityMapper(impl: EntityMapperImpl): EntityMapper
}