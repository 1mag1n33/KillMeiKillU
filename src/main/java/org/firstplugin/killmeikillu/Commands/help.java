package org.firstplugin.killmeikillu.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class help implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage("=== Help ===");
        sender.sendMessage("/command1 - Description of command1");
        sender.sendMessage("/command2 - Description of command2");
        sender.sendMessage("/command3 - Description of command3");
        // Add more commands and their descriptions here
        return true;
    }
}
