package org.firstplugin.killmeikillu;

import org.bukkit.plugin.java.JavaPlugin;

public final class KillMeiKillU extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        System.out.println("My first plugin");

        // Load commands
        CommandLoader.loadCommands(this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("stoped");
    }
}
