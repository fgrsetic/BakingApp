package com.franjo.android.bakingapp.data.di

import com.franjo.android.bakingapp.data.remote.mapper.NetworkErrorMapper
import com.franjo.android.bakingapp.data.remote.mapper.NetworkMapperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface NetworkMapperModule {

    @Binds
    fun bindNetworkMapper(impl: NetworkMapperImpl): NetworkErrorMapper
}