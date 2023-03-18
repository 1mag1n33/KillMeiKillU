package org.firstplugin.killmeikillu;

import org.bukkit.command.CommandSender;

public class hello extends BaseCommand {

    public hello() {
        super("hello");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        sender.sendMessage("Hello, world!");
    }

}