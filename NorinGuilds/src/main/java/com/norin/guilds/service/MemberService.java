package com.norin.guilds.service;

import com.norin.guilds.entity.NorinGuildMember;

import java.util.List;
import java.util.UUID;

public interface MemberService {
    NorinGuildMember integrate(UUID uniqueId, int guildId);

    NorinGuildMember retrieve(UUID uniqueId, int guildId);

    List<NorinGuildMember> retrieveAll(int guildId);

    void disintegrate(UUID uniqueId, int guildId);
}
