package com.norin.guilds.service;

import com.norin.core.service.IEntityService;
import com.norin.core.strategy.EntityStrategy;
import com.norin.guilds.entity.NorinGuild;
import com.norin.rest.common.entity.GuildDTO;
import com.norin.rest.common.requests.GuildCreateRequest;
import com.norin.rest.common.requests.GuildUpdateRequest;
import com.norin.rest.routes.GuildRoute;

import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

public class GuildService implements IEntityService<Integer, NorinGuild> {
    private final GuildRoute guildRoute;
    private final EntityStrategy<GuildDTO, NorinGuild> entityStrategy;

    public GuildService(GuildRoute guildRoute, EntityStrategy<GuildDTO, NorinGuild> entityStrategy) {
        this.guildRoute = guildRoute;
        this.entityStrategy = entityStrategy;
    }

    @Override
    public NorinGuild synchronizedCreate(Integer id) {
        return null;
    }

    @Override
    public void create(Integer id, Consumer<NorinGuild> consumer) {

    }

    @Override
    public NorinGuild synchronizedSearch(Integer id) {
        try {
            return entityStrategy.decode(guildRoute.searchGuild(id).get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void search(Integer id, Consumer<NorinGuild> consumer) {
        guildRoute.searchGuild(id)
                .thenApplyAsync(entityStrategy::decode)
                .thenAcceptAsync(consumer);
    }

    @Override
    public void update(NorinGuild entity) {
        guildRoute.updateGuild(entity.getId(), new GuildUpdateRequest(entity.getName(), entity.getTag(), entity.getOwner().toString()));
    }

    @Override
    public void delete(Integer id) {

    }
}
