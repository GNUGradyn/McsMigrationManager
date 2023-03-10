package com.gradyn.McsMigrationManager;

import com.gradyn.McsMigrationManager.Data.DbFactory;
import com.gradyn.McsMigrationManager.commands.CommandAcceptRules;
import com.gradyn.McsMigrationManager.commands.CommandTransfer;
import com.gradyn.McsMigrationManager.commands.EmptyTabCompleter;
import com.gradyn.McsMigrationManager.commands.TransferTabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;

public class McsMigrationManager extends JavaPlugin {
    public static FileConfiguration config; //declare config.yml for configuration
    @Override
    public void onEnable() {
        loadConfig();
        getLogger().info("Starting MCS Migration Manager");
        DbFactory.buildSessionFactory();
        getServer().getPluginManager().registerEvents(new JoinEvent(), this);
        getCommand("transfer").setExecutor(new CommandTransfer());
        getCommand("transfer").setTabCompleter(new TransferTabCompleter());
        getCommand("acceptrules").setExecutor(new CommandAcceptRules());
        getCommand("acceptrules").setTabCompleter(new EmptyTabCompleter());

    }
    @Override
    public void onDisable() {
        getLogger().info("Stopping MCS Migration Manager");
    }

    private void loadConfig() {
        this.saveDefaultConfig();
        config = this.getConfig();
    }
    public void reloadConfiguration() { // Cannot be called reloadConfig
        this.reloadConfig();
    }

}
