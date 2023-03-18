package org.firstplugin.killmeikillu;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class CommandLoader {

    public static void loadCommands(Plugin plugin) {
        List<Class<?>> classes = getClassesFromJar(plugin);
        for (Class<?> clazz : classes) {
            if (CommandExecutor.class.isAssignableFrom(clazz)) {
                PluginCommand command = Bukkit.getServer().getPluginCommand(clazz.getSimpleName().toLowerCase());
                if (command != null) {
                    // Get the constructor with the required arguments
                    Constructor<?> constructor = clazz.getConstructor(Plugin.class);
                    // Create a new instance of the class with the constructor and plugin argument
                    CommandExecutor executor = (CommandExecutor) constructor.newInstance(plugin);
                    command.setExecutor(executor);
                }
            }
        }
    }

    private static List<Class<?>> getClassesFromJar(Plugin plugin) {
        String packageName = "org.firstplugin.killmeikillu.Commands";
        List<Class<?>> classes = new ArrayList<>();
        URL jarUrl = plugin.getClass().getProtectionDomain().getCodeSource().getLocation();
        try {
            File jarFile = new File(URLDecoder.decode(jarUrl.getFile(), "UTF-8"));
            if (jarFile.isDirectory()) {
                // Development mode - classes are on disk
                File commandsFolder = new File(plugin.getDataFolder(), "Commands");
                if (!commandsFolder.exists() || !commandsFolder.isDirectory()) {
                    return Collections.emptyList();
                }
                File[] files = commandsFolder.listFiles((dir, name) -> name.endsWith(".class"));
                if (files != null) {
                    for (File file : files) {
                        String className = packageName + "." + file.getName().replace(".class", "");
                        try {
                            Class<?> clazz = Class.forName(className);
                            classes.add(clazz);
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } else {
                // Production mode - classes are in jar file
                try (JarFile jar = new JarFile(jarFile)) {
                    for (JarEntry entry : Collections.list(jar.entries())) {
                        String name = entry.getName().replace("/", ".");
                        if (name.startsWith(packageName) && name.endsWith(".class")) {
                            String className = name.substring(0, name.length() - 6);
                            try {
                                Class<?> clazz = Class.forName(className);
                                classes.add(clazz);
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        return classes;
    }
}
