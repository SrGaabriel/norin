package com.norin.guilds.strategy;

import com.norin.core.strategy.EntityStrategy;
import com.norin.guilds.entity.NorinGuildMember;
import com.norin.rest.common.entity.GuildMemberDTO;
import com.norin.rest.common.util.jvm.UniqueIdKt;

public class GuildMemberEntityStrategy implements EntityStrategy<GuildMemberDTO, NorinGuildMember> {
    @Override
    public GuildMemberDTO encode(NorinGuildMember entity) {
        return new GuildMemberDTO(
                entity.getUniqueId().toString(),
                entity.getGuildId(),
                entity.getPosition()
        );
    }

    @Override
    public NorinGuildMember decode(GuildMemberDTO dto) {
        return new NorinGuildMember(
                UniqueIdKt.getPrettyUUID(dto.getUniqueId()),
                dto.getGuildId(),
                dto.getPosition()
        );
    }
}
