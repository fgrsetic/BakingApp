package com.franjo.android.bakingapp.domain.usecase.base;

import io.reactivex.rxjava3.core.Completable;

public interface CompletableUseCase {
    Completable execute();
}
