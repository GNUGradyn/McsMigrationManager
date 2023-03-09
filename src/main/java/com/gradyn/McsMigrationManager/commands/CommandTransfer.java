package com.gradyn.McsMigrationManager.commands;

import com.gradyn.McsMigrationManager.PlotMigrator;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.UUID;

public class CommandTransfer implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (args.length != 2) return false;

        UUID newGuid = Bukkit.getOfflinePlayer(args[0]).getUniqueId();
        UUID oldGuid = PlotMigrator.GetPsudoUUID(args[1]);
        if (oldGuid == null) {
            sender.sendMessage("Historical user not found");
            return true;
        }

        PlotMigrator.MigratePlots(oldGuid, newGuid);

        sender.sendMessage("Migration successful");
        return true;
    }
}
