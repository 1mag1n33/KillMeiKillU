package org.firstplugin.killmeikillu;

import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.InvocationTargetException;

public final class KillMeiKillU extends JavaPlugin {

    @Override
    public void onEnable() {

        //Loads commands
        this.getCommand("hello").setExecutor(new hello());

        //Output

        System.out.println("My first plugin");


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("stopped");
    }
}
