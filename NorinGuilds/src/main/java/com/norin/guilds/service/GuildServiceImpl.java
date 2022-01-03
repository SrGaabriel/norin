package com.norin.guilds.service;

import com.norin.core.strategy.EntityStrategy;
import com.norin.guilds.entity.NorinGuild;
import com.norin.guilds.util.GuildFactory;
import com.norin.rest.common.entity.GuildDTO;
import com.norin.rest.common.requests.GuildCreateRequest;
import com.norin.rest.common.requests.GuildUpdateRequest;
import com.norin.rest.routes.GuildRoute;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;

public class GuildServiceImpl implements GuildService {
    private final GuildRoute guildRoute;
    private final EntityStrategy<GuildDTO, NorinGuild> entityStrategy;
    private static final Map<Integer, ReentrantLock> mutexes = new HashMap<>();

    public GuildServiceImpl(GuildRoute guildRoute, EntityStrategy<GuildDTO, NorinGuild> entityStrategy) {
        this.guildRoute = guildRoute;
        this.entityStrategy = entityStrategy;
    }

    @Override
    public void create(GuildFactory factory, Consumer<NorinGuild> guildConsumer) {
        guildRoute.createGuild(new GuildCreateRequest(
                factory.getName(),
                factory.getTag(),
                factory.getOwner().toString()
        )).thenApplyAsync(entityStrategy::decode).thenAcceptAsync(guildConsumer);
    }

    @Override
    public void search(int id, Consumer<NorinGuild> guildConsumer) {
        guildRoute.searchGuild(id)
                .thenApplyAsync(entityStrategy::decode)
                .thenAcceptAsync(guildConsumer);
    }

    @Override
    public void searchByTag(String tag, Consumer<NorinGuild> guildConsumer) {
        guildRoute.searchGuildByTag(tag)
                .thenApplyAsync(entityStrategy::decode)
                .thenAcceptAsync(guildConsumer);
    }

    @Override
    public void replace(NorinGuild guild) {
        guildRoute.updateGuild(guild.getId(), new GuildUpdateRequest(
                guild.getName(),
                guild.getTag(),
                guild.getOwner().toString(),
                guild.isFriendlyFireAllowed()
        ));
    }

    @Override
    public void delete(int id) {
        guildRoute.deleteGuild(id);
    }

    @Deprecated
    // TODO: Improve and move
    protected static ReentrantLock getGuildMutex(int id) {
        ReentrantLock mutex = mutexes.containsKey(id) ? mutexes.get(id) : new ReentrantLock();
        if (!mutexes.containsKey(id)) mutexes.put(id, mutex);
        return mutex;
    }
}
