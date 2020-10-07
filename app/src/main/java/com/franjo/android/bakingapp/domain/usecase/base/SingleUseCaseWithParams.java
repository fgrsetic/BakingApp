package com.franjo.android.bakingapp.domain.usecase.base;

import io.reactivex.rxjava3.core.Single;

public interface SingleUseCaseWithParams<P, R> {
    Single<R> execute(P parameter);
}
