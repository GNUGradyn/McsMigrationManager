package com.gradyn.McsMigrationManager;

import org.bukkit.plugin.java.JavaPlugin;

public class McsMigrationManager extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("Starting MCS Migration Manager");
    }
    @Override
    public void onDisable() {
        getLogger().info("Stopping MCS Migration Manager");
    }
}
