package com.norin.core.strategy;

public interface EntityStrategy<T, A> {
    T encode(A entity);

    A decode(T dto);
}
