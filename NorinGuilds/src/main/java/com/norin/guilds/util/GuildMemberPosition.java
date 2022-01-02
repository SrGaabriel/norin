package com.norin.guilds.util;

public enum GuildMemberPosition {
    LEADER(3),
    CAPTAIN(2),
    MEMBER(1),
    ROOKIE(0);

    private final int id;
    GuildMemberPosition(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static GuildMemberPosition fromId(int id) {
        return switch (id) {
            case 3 -> LEADER;
            case 2 -> CAPTAIN;
            case 1 -> MEMBER;
            case 0 -> ROOKIE;
            default -> null;
        };
    }
}
