package com.gradyn.McsMigrationManager.commands;

import com.gradyn.McsMigrationManager.ArchiveMgr;
import com.gradyn.McsMigrationManager.Data.DbFactory;
import com.gradyn.McsMigrationManager.Data.Models.Archive;
import com.gradyn.McsMigrationManager.Data.Models.UserCache;
import com.gradyn.McsMigrationManager.McsMigrationManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.hibernate.Session;
import org.jetbrains.annotations.NotNull;

public class CommandArchive implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(sender instanceof  Player)) {
            sender.sendMessage("Only in game players can use this command");
            return true;
        }

        // make sure a username was provided
        if (args.length == 0) return false;

        // Figure out which plot to go to
        int number = 1;
        try {
            if (args.length > 1) number = Integer.parseInt(args[1]);
        } catch (NumberFormatException ex) {
            return false;
        }

        // Find all the plots under this name
        var results = ArchiveMgr.LocateByUsername(args[0]);

        if (results.length == 0) {
            sender.sendMessage("No plots found for user " + args[0]);
            return true;
        }

        // Make sure the plot requested actually exists
        if (results.length < number) {
            sender.sendMessage("User only has " + args[1] + " plots.");
            return true;
        }

        // GOOOOOOOOOOOO
        var player = (Player) sender;
        var location = new Location(
                Bukkit.getWorld(McsMigrationManager.config.getString("archiveWorld")),
                results[number].getX(),
                results[number].getY(),
                results[number].getZ(),
                0,
                0
                );

        player.teleport(location);
        return true;
    }
}
