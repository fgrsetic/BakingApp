package com.franjo.android.bakingapp.utilities.schedulers;


import io.reactivex.rxjava3.core.Scheduler;

public interface BaseSchedulerProvider {

    Scheduler getIOScheduler();

    Scheduler getComputerScheduler();

    Scheduler getUIScheduler();
}
