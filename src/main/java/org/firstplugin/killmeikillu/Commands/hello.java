package org.firstplugin.killmeikillu.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class hello implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {



        public void onEnable() {
            // Register the "hello" command
            PluginCommand commands = getCommand("hello");
            if (command != null) {
                command.setExecutor(new hello());
            }
        }

        sender.sendMessage("Hello, world!");

        return true;
    }
}
