package com.franjo.android.bakingapp.domain.usecase.base;

import io.reactivex.rxjava3.core.Observable;

public interface UseCaseWithParams<P, R> {
    Observable<R> execute(P parameter);
}
