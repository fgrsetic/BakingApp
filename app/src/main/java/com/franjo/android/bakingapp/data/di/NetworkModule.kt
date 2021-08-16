package com.franjo.android.bakingapp.data.di

import com.franjo.android.bakingapp.BuildConfig.DEBUG
import com.franjo.android.bakingapp.data.remote.service.ApiService
import com.franjo.android.bakingapp.data.remote.service.Endpoint
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    // Moshi object that Retrofit will be using with Kotlin adapter
    // for full Kotlin compatibility
    @Provides
    @Singleton
    fun providesMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    // OkHttp logging messages -> verbose logcat
    @Provides
    @Singleton
    fun providesLoggingInterceptor(): Interceptor {
        val logger = HttpLoggingInterceptor()
        logger.setLevel(HttpLoggingInterceptor.Level.HEADERS)
        logger.setLevel(HttpLoggingInterceptor.Level.BODY)
        return logger
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        if (DEBUG) {
            builder.addInterceptor(HttpLoggingInterceptor())
        }
        return builder.build()
    }


    @Provides
    @Singleton
    fun providesRetrofit(client: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(Endpoint.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun providesService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

}