package com.norin.core.service;

import java.util.function.Consumer;

public interface IEntityService<I, E> {
    @Deprecated
    E synchronizedCreate(I id);

    void create(I id, Consumer<E> consumer);

    @Deprecated
    E synchronizedSearch(I id);

    void search(I id, Consumer<E> consumer);

    void update(E entity);

    void delete(I id);
}
