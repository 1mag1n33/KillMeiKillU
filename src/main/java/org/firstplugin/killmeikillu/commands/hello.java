package org.firstplugin.killmeikillu.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class hello implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // Your command logic goes here
        sender.sendMessage("Hello, world!");

        return true;
    }
}
