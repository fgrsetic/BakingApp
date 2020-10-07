package com.franjo.android.bakingapp.app.di;

import android.app.Application;
import android.content.Context;

import com.franjo.android.bakingapp.app.BakingApplication;
import com.franjo.android.bakingapp.data.di.NetworkModule;
import com.franjo.android.bakingapp.presentation.di.ActivityModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        ApplicationModule.class,
        NetworkModule.class,
        ActivityModule.class
})
public interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        ApplicationComponent create(@BindsInstance Application application);
    }

    void inject(BakingApplication bakingApplication);
}

