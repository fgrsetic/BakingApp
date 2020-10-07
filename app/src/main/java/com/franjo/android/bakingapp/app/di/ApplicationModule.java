package com.franjo.android.bakingapp.app.di;

import android.content.Context;

import com.franjo.android.bakingapp.app.BakingApplication;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;
import javax.inject.Scope;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.MustBeDocumented;

@Module
public class ApplicationModule {

    @Provides
    @Singleton
    public Context provideApplicationContext(BakingApplication application) {
        return application;
    }
}





