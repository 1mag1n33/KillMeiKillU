package org.firstplugin.killmeikillu;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.Objects;

public class CommandLoader {

    public static void loadCommands(Plugin plugin) {
        String packageName = plugin.getName() + ".Commands";
        File packageFolder = new File(plugin.getDataFolder(), "Commands");
        if (!packageFolder.exists()) {
            packageFolder.mkdir();
        }

        File[] files = packageFolder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".class")) {
                    String className = file.getName().substring(0, file.getName().lastIndexOf("."));
                    try {
                        Class<?> clazz = Class.forName(packageName + "." + className);
                        if (CommandExecutor.class.isAssignableFrom(clazz)) {
                            PluginCommand command = Bukkit.getServer().getPluginCommand(className.toLowerCase());
                            if (command != null) {
                                command.setExecutor((CommandExecutor) clazz.getConstructor().newInstance());
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
