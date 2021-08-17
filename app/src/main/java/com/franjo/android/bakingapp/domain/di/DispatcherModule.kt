package com.franjo.android.bakingapp.domain.di

import com.franjo.android.bakingapp.domain.utils.DispatcherProvider
import com.franjo.android.bakingapp.domain.utils.DispatcherProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton


@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class DefaultDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class IODispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MainDispatcher

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {

//    @IODispatcher
//    @Provides
//    fun iODispatcher(): CoroutineDispatcher = Dispatchers.IO
//
//    @DefaultDispatcher
//    @Provides
//    fun defaultDispatcher(): CoroutineDispatcher = Dispatchers.Default
//
//    @MainDispatcher
//    @Provides
//    fun mainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @Singleton
    @Provides
    fun providesDispatcher(): DispatcherProvider = DispatcherProviderImpl()

}

