package com.franjo.android.bakingapp.app;

import android.app.Application;

import com.franjo.android.bakingapp.app.di.ApplicationComponent;
import com.franjo.android.bakingapp.app.di.DaggerApplicationComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;

public class BakingApplication extends Application implements HasAndroidInjector {

    @Inject
    DispatchingAndroidInjector<Object> androidInjector;

    @Override
    public AndroidInjector<Object> androidInjector() {
        return androidInjector;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // Here we use our DaggerApplicationComponent to inject our Application class
        // This way a DispatchingAndroidInjector is injected which is then returned
        // when an injector for an activity is requested through androidInjector
        ApplicationComponent applicationComponent = DaggerApplicationComponent.factory().create(this);
        applicationComponent.inject(this);

    }
}
