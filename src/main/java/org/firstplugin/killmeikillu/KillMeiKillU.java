package org.firstplugin.killmeikillu;

import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.InvocationTargetException;

public final class KillMeiKillU extends JavaPlugin {

    private String commandPrefix = "/KMKU"; // Set the command prefix

    @Override
    public void onEnable() {
        getLogger().info("KillMeiKillU is enabled!");

        // Register commands
        getCommand("KMKU").setExecutor(new BaseCommand());
        getCommand("KMKU").setTabCompleter(new BaseCommand());
        getCommand("hello").setExecutor(new hello());
    }

    // Get the command prefix
    public String getCommandPrefix() {
        return commandPrefix;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("stopped");
    }
}
