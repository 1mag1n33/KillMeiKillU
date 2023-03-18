package org.firstplugin.killmeikillu;

import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.InvocationTargetException;

public final class KillMeiKillU extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        System.out.println("My first plugin");

        // Load commands
        try {
            CommandLoader.loadCommands(this);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("stoped");
    }
}
