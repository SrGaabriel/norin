package com.norin.guilds.strategy;

import com.norin.core.strategy.EntityStrategy;
import com.norin.guilds.entity.NorinGuild;
import com.norin.guilds.entity.NorinGuildMember;
import com.norin.rest.common.entity.GuildDTO;
import com.norin.rest.common.entity.GuildMemberDTO;
import com.norin.rest.common.util.jvm.UniqueIdKt;

public class GuildEntityStrategy implements EntityStrategy<GuildDTO, NorinGuild> {
    @Override
    public GuildDTO encode(NorinGuild entity) {
        return new GuildDTO(
                entity.getId(),
                entity.getName(),
                entity.getTag(),
                entity.getOwner().toString(),
                entity.isFriendlyFireAllowed()
        );
    }

    @Override
    public NorinGuild decode(GuildDTO dto) {
        return new NorinGuild(
                dto.getId(),
                dto.getName(),
                dto.getTag(),
                UniqueIdKt.getPrettyUUID(dto.getOwnerId()),
                dto.getFriendlyFire()
        );
    }
}
