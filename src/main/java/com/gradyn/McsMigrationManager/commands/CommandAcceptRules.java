package com.gradyn.McsMigrationManager.commands;

import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.node.Node;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandAcceptRules implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        var luckperms = LuckPermsProvider.get();
        var player = (Player) commandSender;
        var user = luckperms.getPlayerAdapter(Player.class).getUser(player);
        if (commandSender.hasPermission("group.constructor")) {
            commandSender.sendMessage("You already accepted the rules!");
            return true;
        }
        user.data().add(Node.builder("group.constructor").build());
        luckperms.getUserManager().saveUser(user);
        player.sendMessage("Thank you for accepting the rules! You are now a constructor!");
        return true;
    }
}
