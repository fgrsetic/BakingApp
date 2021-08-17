package com.franjo.android.bakingapp.domain.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class DispatcherProviderImpl : DispatcherProvider {

    override fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    override fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    override fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default
}