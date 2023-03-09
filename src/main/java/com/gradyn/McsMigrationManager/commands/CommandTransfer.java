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

        UUID oldGuid = PlotMigrator.GetPsudoUUID(args[0]);
        UUID newGuid = Bukkit.getOfflinePlayer(args[1]).getUniqueId();
        if (oldGuid == null) {
            sender.sendMessage("Historical user not found");
            return true;
        }

        var success = PlotMigrator.MigratePlots(oldGuid, newGuid);

        if (success) sender.sendMessage("Migration successful");
        else sender.sendMessage("Migration failed. See console for details");
        return true;
    }
}
