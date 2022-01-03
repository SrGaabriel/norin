package com.norin.core.service;

import com.norin.core.entity.NorinAccount;
import com.norin.core.strategy.EntityStrategy;
import com.norin.rest.NorinRestWrapper;
import com.norin.rest.common.entity.AccountDTO;
import com.norin.rest.common.requests.AccountCreateRequest;
import com.norin.rest.common.requests.AccountUpdateRequest;
import com.norin.rest.routes.AccountRoute;

import java.util.UUID;
import java.util.function.Consumer;

public class AccountServiceImpl implements AccountService {
    private final AccountRoute accountRoute;
    private final EntityStrategy<AccountDTO, NorinAccount> entityStrategy;
    public AccountServiceImpl(NorinRestWrapper restWrapper, EntityStrategy<AccountDTO, NorinAccount> entityStrategy) {
        this.accountRoute = new AccountRoute(restWrapper);
        this.entityStrategy = entityStrategy;
    }

    @Override
    public void create(UUID uniqueId, Consumer<NorinAccount> consumer) {
        accountRoute.createAccount(new AccountCreateRequest(uniqueId.toString()))
                .thenApplyAsync(entityStrategy::decode)
                .thenAcceptAsync(consumer);
    }

    @Override
    public void search(UUID uniqueId, Consumer<NorinAccount> consumer) {
        accountRoute.searchAccount(uniqueId.toString())
                .thenApplyAsync(entityStrategy::decode)
                .thenAcceptAsync(consumer);
    }

    @Override
    public void update(NorinAccount account) {
        accountRoute.updateAccount(account.getUniqueId().toString(), new AccountUpdateRequest(
                account.getCash(),
                account.getGuild(),
                account.getLoginDate().toEpochMilli()
        ));
    }

    @Override
    public void delete(UUID uniqueId) {
        accountRoute.deleteAccount(uniqueId.toString());
    }
}
