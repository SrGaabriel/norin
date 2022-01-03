package com.norin.guilds.entity;

import com.norin.rest.common.GuildMemberPosition;

import java.util.UUID;

public class NorinGuildMember {
    private final UUID uniqueId;
    private final int guildId;
    private GuildMemberPosition position;

    public NorinGuildMember(UUID uniqueId, int guildId, GuildMemberPosition position) {
        this.uniqueId = uniqueId;
        this.guildId = guildId;
        this.position = position;
    }

    public UUID getUniqueId() {
        return uniqueId;
    }

    public int getGuildId() {
        return guildId;
    }

    public GuildMemberPosition getPosition() {
        return position;
    }

    public void setPosition(GuildMemberPosition position) {
        this.position = position;
    }
}
