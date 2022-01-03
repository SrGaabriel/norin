package com.norin.guilds.entity;

import java.util.List;
import java.util.UUID;

public class NorinGuild {
    private final int id;
    private String name;
    private String tag;
    private UUID owner;

    public NorinGuild(int id, String name, String tag, UUID owner) {
        this.id = id;
        this.name = name;
        this.tag = tag;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public UUID getOwner() {
        return owner;
    }

    public void setOwner(UUID owner) {
        this.owner = owner;
    }
}
