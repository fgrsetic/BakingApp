package com.franjo.android.bakingapp.utilities.schedulers;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SchedulerProvider implements BaseSchedulerProvider {

    @Override
    public Scheduler getIOScheduler() {
        return Schedulers.io();
    }

    @Override
    public Scheduler getComputerScheduler() {
        return Schedulers.computation();
    }

    @Override
    public Scheduler getUIScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
