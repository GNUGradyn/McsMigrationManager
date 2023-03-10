package com.gradyn.McsMigrationManager.commands;

import net.luckperms.api.LuckPermsProvider;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandAcceptRules implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        var luckperms = LuckPermsProvider.get();
        if (commandSender.hasPermission("group.constructor")) {
            commandSender.sendMessage("You already accepted the rules!");
            return true;
        }
        var player = (Player) commandSender;
        var user = luckperms.getUserManager().getUser(player.getUniqueId());
        user.setPrimaryGroup("constructor");
        player.sendMessage("Thank you for accepting the rules! You are now a constructor!");
        return true;
    }
}
