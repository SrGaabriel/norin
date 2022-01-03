package com.norin.guilds.service;

import com.norin.core.strategy.EntityStrategy;
import com.norin.guilds.entity.NorinGuildMember;
import com.norin.rest.common.entity.GuildMemberDTO;
import com.norin.rest.common.requests.MemberJoinGuildRequest;
import com.norin.rest.routes.MemberRoute;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class MemberServiceImpl implements MemberService {
    private final MemberRoute memberRoute;
    private final EntityStrategy<GuildMemberDTO, NorinGuildMember> memberStrategy;

    public MemberServiceImpl(MemberRoute memberRoute, EntityStrategy<GuildMemberDTO, NorinGuildMember> memberStrategy) {
        this.memberRoute = memberRoute;
        this.memberStrategy = memberStrategy;
    }

    @Override
    public NorinGuildMember integrate(UUID uniqueId, int guildId) {
        return memberRoute.integrateMember(new MemberJoinGuildRequest(
                uniqueId.toString(),
                guildId
        )).thenApply(memberStrategy::decode).join();
    }

    @Override
    public NorinGuildMember retrieve(UUID uniqueId, int guildId) {
        return memberStrategy.decode(memberRoute.retrieveMembership(uniqueId.toString(), guildId).join());
    }

    @Override
    public List<NorinGuildMember> retrieveAll(int guildId) {
        return memberRoute.retrieveAllMemberships(guildId)
                .thenApply((members) -> members.stream().map(memberStrategy::decode))
                .thenApply((members) -> members.collect(Collectors.toList()))
                .join();
    }

    @Override
    public void disintegrate(UUID uniqueId, int guildId) {
        memberRoute.disintegrateMember(uniqueId.toString(), guildId);
    }
}
