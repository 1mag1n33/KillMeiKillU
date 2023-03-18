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
                        String commandName = "ikill" + className.toLowerCase();
                        PluginCommand command = Bukkit.getServer().getPluginCommand(commandName);
                        if (command != null) {
                            command.setName(commandName); // add prefix to command name
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
