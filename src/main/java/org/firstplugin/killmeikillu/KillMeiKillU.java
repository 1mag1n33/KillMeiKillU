package org.firstplugin.killmeikillu;

import org.bukkit.plugin.java.JavaPlugin;

public final class KillMeiKillU extends JavaPlugin {


    @Override
    public void onEnable() {
        getLogger().info("KillMeiKillU is enabled!");
        getCommand("kmku").setExecutor(new BaseCommand());
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("stopped");
    }
}
