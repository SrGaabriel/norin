package com.norin.core.service;

import com.norin.core.entity.NorinAccount;

import java.util.UUID;
import java.util.function.Consumer;

public interface AccountService {
    void create(UUID id, Consumer<NorinAccount> consumer);

    void search(UUID id, Consumer<NorinAccount> consumer);

    void update(NorinAccount entity);

    void delete(UUID id);
}
