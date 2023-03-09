package com.gradyn.McsMigrationManager.commands;

import com.gradyn.McsMigrationManager.PlotMigrator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.List;

public class TransferTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        if (args.length == 1) {
            return PlotMigrator.UsernameSearch(args[0]);
        }
        return null;
    }
}
