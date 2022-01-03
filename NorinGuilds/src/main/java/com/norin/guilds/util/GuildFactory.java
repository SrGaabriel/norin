package com.norin.guilds.util;

import java.util.UUID;

public class GuildFactory {
    private final String name, tag;
    private final UUID owner;

    public GuildFactory(String name, String tag, UUID owner) {
        this.name = name;
        this.tag = tag;
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public String getTag() {
        return tag;
    }

    public UUID getOwner() {
        return owner;
    }
}
