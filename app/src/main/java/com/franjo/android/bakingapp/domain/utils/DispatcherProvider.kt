package com.franjo.android.bakingapp.domain.utils

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvider {
    fun provideMainDispatcher(): CoroutineDispatcher
    fun provideIODispatcher(): CoroutineDispatcher
    fun provideDefaultDispatcher(): CoroutineDispatcher
}