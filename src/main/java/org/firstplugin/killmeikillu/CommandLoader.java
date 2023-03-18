package org.firstplugin.killmeikillu;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.Objects;

public class CommandLoader {

    public static void loadCommands(Plugin plugin) {
        File commandsFolder = new File(plugin.getDataFolder(), "commands");
        if (!commandsFolder.exists()) {
            commandsFolder.mkdirs();
        }
        for (File file : Objects.requireNonNull(commandsFolder.listFiles())) {
            if (file.isFile() && file.getName().endsWith(".java")) {
                String className = file.getName().substring(0, file.getName().lastIndexOf("."));
                try {
                    Class<?> clazz = Class.forName("org.firstplugin.killmeikillu.commands." + className);
                    if (CommandExecutor.class.isAssignableFrom(clazz)) {
                        PluginCommand command = Bukkit.getServer().getPluginCommand(clazz.getSimpleName().toLowerCase());
                        if (command == null) {
                            command = Bukkit.getServer().getPluginCommand(className.toLowerCase());
                        }
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
