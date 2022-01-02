package com.norin.core.service;

import com.norin.core.entity.NorinAccount;
import com.norin.core.strategy.EntityStrategy;
import com.norin.rest.NorinRestWrapper;
import com.norin.rest.common.entity.AccountDTO;
import com.norin.rest.common.requests.AccountCreateRequest;
import com.norin.rest.common.requests.AccountUpdateRequest;
import com.norin.rest.routes.AccountRoute;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;

public class AccountService implements IEntityService<UUID, NorinAccount> {
    private final AccountRoute accountRoute;
    private final Map<UUID, ReentrantLock> mutexes = new HashMap<>();

    private final EntityStrategy<AccountDTO, NorinAccount> entityStrategy;
    public AccountService(NorinRestWrapper restWrapper, EntityStrategy<AccountDTO, NorinAccount> entityStrategy) {
        this.accountRoute = new AccountRoute(restWrapper);
        this.entityStrategy = entityStrategy;
    }

    @Override
    @Deprecated
    public synchronized NorinAccount synchronizedCreate(UUID uniqueId) {
        try {
            return entityStrategy.decode(
                    accountRoute.createAccount(new AccountCreateRequest(uniqueId.toString())).get()
            );
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void create(UUID uniqueId, Consumer<NorinAccount> consumer) {
        final ReentrantLock mutex = getAccountMutex(uniqueId);
        mutex.lock();
        accountRoute.createAccount(new AccountCreateRequest(uniqueId.toString()))
                .thenApplyAsync(entityStrategy::decode)
                .thenAcceptAsync(consumer);
        mutex.unlock();
    }

    @Override
    @Deprecated
    public synchronized NorinAccount synchronizedSearch(UUID uniqueId) {
        NorinAccount account = null;
        try {
            account = entityStrategy.decode(
                    accountRoute.createAccount(new AccountCreateRequest(uniqueId.toString())).get()
            );
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return account;
    }

    @Override
    public void search(UUID uniqueId, Consumer<NorinAccount> consumer) {
        final ReentrantLock mutex = getAccountMutex(uniqueId);
        mutex.lock();
        accountRoute.searchAccount(uniqueId.toString())
                .thenApplyAsync(entityStrategy::decode)
                .thenAcceptAsync(consumer);
        mutex.unlock();
    }

    @Override
    public void update(NorinAccount account) {
        accountRoute.updateAccount(account.getUniqueId().toString(), new AccountUpdateRequest(
                account.getCash(),
                account.getLoginDate().toEpochMilli()
        ));
    }

    @Override
    public void delete(UUID uniqueId) {
        final ReentrantLock mutex = getAccountMutex(uniqueId);
        mutex.lock();
        accountRoute.deleteAccount(uniqueId.toString());
        mutex.unlock();
    }

    private ReentrantLock getAccountMutex(UUID uniqueId) {
        ReentrantLock mutex = mutexes.containsKey(uniqueId) ? mutexes.get(uniqueId) : new ReentrantLock();;
        if (!mutexes.containsKey(uniqueId)) mutexes.put(uniqueId, mutex);
        return mutex;
    }
}
