package org.firstplugin.killmeikillu;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BaseCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("KillMeiKillU main command");
            sender.sendMessage("Usage: /kmku <subcommand> [args]");
            sender.sendMessage("Type /kmku help for a list of subcommands.");
            return true;
        }

        switch (args[0]) {
            case "hello":
                return new hello().onCommand(sender, command, label, Arrays.copyOfRange(args, 1, args.length));
            case "help":
                return new help().onCommand(sender, command, label, Arrays.copyOfRange(args, 1, args.length));
            case "Target":
                return new KillAll().onCommand(sender, command, label, Arrays.copyOfRange(args, 1, args.length));
            default:
                sender.sendMessage("Unknown subcommand: " + args[0]);
                sender.sendMessage("Type /kmku help for a list of subcommands.");
                return true;
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            return Arrays.asList("hello","help","Target");
        }
        return Collections.emptyList();
    }
}
