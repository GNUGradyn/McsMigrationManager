package com.gradyn.McsMigrationManager.commands;

import com.gradyn.McsMigrationManager.ArchiveMgr;
import com.gradyn.McsMigrationManager.PlotMigrator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ArchiveTabCompleter implements TabCompleter {
    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (args.length == 1) {
            if (args[0].length() == 0) return new ArrayList<String>();
            return ArchiveMgr.UsernameSearch(args[0]);
        }
        return new ArrayList<String>();
    }
}
