package com.norin.guilds;

import com.norin.core.NorinCore;
import com.norin.guilds.listener.GuildOptionsListener;
import com.norin.guilds.service.GuildService;
import com.norin.guilds.service.GuildServiceImpl;
import com.norin.guilds.service.MemberService;
import com.norin.guilds.service.MemberServiceImpl;
import com.norin.guilds.strategy.GuildEntityStrategy;
import com.norin.guilds.strategy.GuildMemberEntityStrategy;
import com.norin.rest.NorinRestWrapper;
import com.norin.rest.routes.GuildRoute;
import com.norin.rest.routes.MemberRoute;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class NorinGuilds extends JavaPlugin {
    private GuildService guildService;
    private MemberService memberService;

    @Override
    public void onEnable() {
        final NorinCore core = NorinCore.getInstance();
        final NorinRestWrapper restWrapper = core.getRestWrapper();
        guildService = new GuildServiceImpl(new GuildRoute(restWrapper), new GuildEntityStrategy());
        memberService = new MemberServiceImpl(new MemberRoute(restWrapper), new GuildMemberEntityStrategy());

        Bukkit.getPluginManager().registerEvents(new GuildOptionsListener(core, this), this);
    }

    public GuildService getGuildService() {
        return guildService;
    }

    public void setGuildService(GuildService guildService) {
        this.guildService = guildService;
    }

    public MemberService getMemberService() {
        return memberService;
    }

    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

    public static NorinGuilds getInstance() {
        return NorinGuilds.getPlugin(NorinGuilds.class);
    }
}
