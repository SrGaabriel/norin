package com.norin.core.entity;

import java.time.Instant;
import java.util.UUID;

public class NorinAccount {
    private final UUID uniqueId;
    private int cash;
    // Must be nullable, so we can't use primitive int here
    private Integer guildId;
    private Instant loginDate;
    private final Instant creationDate;

    public NorinAccount(UUID uniqueId, Instant creationDate) {
        this.uniqueId = uniqueId;
        this.creationDate = creationDate;
    }

    public NorinAccount(UUID uniqueId, int cash, int guildId, Instant loginDate, Instant creationDate) {
        this.uniqueId = uniqueId;
        this.cash = cash;
        this.guildId = guildId;
        this.loginDate = loginDate;
        this.creationDate = creationDate;
    }

    public UUID getUniqueId() {
        return uniqueId;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public Integer getGuild() {
        return guildId;
    }

    public void setGuild(Integer guildId) {
        this.guildId = guildId;
    }

    public Instant getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Instant loginDate) {
        this.loginDate = loginDate;
    }

    public Instant getCreationDate() {
        return creationDate;
    }
}
