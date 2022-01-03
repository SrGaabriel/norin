package com.norin.guilds.listener;

import com.norin.core.NorinCore;
import com.norin.core.service.AccountService;
import com.norin.guilds.NorinGuilds;
import com.norin.guilds.service.GuildService;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class GuildOptionsListener implements Listener {
    private final NorinCore core;
    private final NorinGuilds plugin;
    public GuildOptionsListener(NorinCore core, NorinGuilds plugin) {
        this.core = core;
        this.plugin = plugin;
    }
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player receiver && event.getDamager() instanceof Player damager))
            return;
        final AccountService accountService = core.getAccountService();
        final GuildService guildService = plugin.getGuildService();
        // TODO: ugly, fix
        accountService.search(receiver.getUniqueId(), (receiverAccount) -> {
            // Don't waste any more resources if receiver doesn't have a guild to begin with
            if (receiverAccount.getGuild() == null) return;
            accountService.search(damager.getUniqueId(), (damagerAccount) -> {
                if (!receiverAccount.getGuild().equals(damagerAccount.getGuild()))
                    return;
                guildService.search(receiverAccount.getGuild(), (guild) -> {
                    if (guild.isFriendlyFireAllowed())
                        return;
                    event.setCancelled(true);
                });
            });
        });
    }
}
