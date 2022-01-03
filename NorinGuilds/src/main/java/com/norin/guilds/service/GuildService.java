package com.norin.guilds.service;

import com.norin.guilds.entity.NorinGuild;
import com.norin.guilds.util.GuildFactory;

import java.util.function.Consumer;

public interface GuildService {

    void create(GuildFactory factory, Consumer<NorinGuild> guildConsumer);

    void search(int id, Consumer<NorinGuild> guildConsumer);

    void searchByTag(String tag, Consumer<NorinGuild> guildConsumer);

    void replace(NorinGuild guild);

    void delete(int id);
}
