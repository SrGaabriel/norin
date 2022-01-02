package com.norin.core;

import com.norin.rest.NorinRestWrapper;
import com.norin.core.service.AccountService;
import com.norin.core.strategy.AccountEntityStrategy;
import org.bukkit.plugin.java.JavaPlugin;

public class NorinCore extends JavaPlugin {
    private NorinRestWrapper restWrapper;
    private AccountService accountService;

    @Override
    public void onEnable() {
        loadRestWrapper();
    }

    public void loadRestWrapper() {
        restWrapper = new NorinRestWrapper("http://localhost:8080/api", "key");
        accountService = new AccountService(restWrapper, new AccountEntityStrategy());
    }

    public NorinRestWrapper getRestWrapper() {
        return restWrapper;
    }

    public AccountService getAccountService() {
        return accountService;
    }
}
