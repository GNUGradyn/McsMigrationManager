package com.gradyn.McsMigrationManager;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class JoinEvent implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID psudoUuid = PlotMigrator.GetPsudoUUID(player.getName());
        if (psudoUuid != null) PlotMigrator.MigratePlots(psudoUuid, player.getUniqueId());
        player.sendMessage("Your plots have been auto migrated :)");
    }
}
