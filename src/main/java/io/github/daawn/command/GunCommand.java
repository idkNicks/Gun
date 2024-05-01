package io.github.daawn.command;

import io.github.daawn.GunPlugin;
import io.github.daawn.command.impl.GetGunCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabExecutor;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class GunCommand implements TabExecutor {

    private final Map<String, SubCommand> subCommandMap = new HashMap<>();

    public GunCommand(GunPlugin gunPlugin) {
        PluginCommand command = gunPlugin.getCommand("gun");
        if (command != null) {
            command.setExecutor(this);
            command.setTabCompleter(this);
            registerSubCommands();
        }
    }

    private void registerSubCommands() {
        registerSubCommand(new GetGunCommand());
    }

    private void registerSubCommand(SubCommand subCommand) {
        subCommandMap.put(subCommand.getName(), subCommand);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) {
            Set<SubCommand> uniqueSubCommands = new HashSet<>(subCommandMap.values());

            sender.sendMessage(" §6§m                                         §r");
            for (SubCommand subCommand : uniqueSubCommands) {
                sender.sendMessage(" §e§l| §f/총 " + subCommand.getName() + " " + subCommand.getUsage() + " : " + subCommand.getDescription());
            }
            sender.sendMessage("");
            return true;
        }

        SubCommand subCmdInstance = subCommandMap.get(args[0].toLowerCase());
        if (subCmdInstance != null && subCmdInstance.hasPermission(sender)) {
            subCmdInstance.execute(sender, label, args);
            return true;
        }

        sender.sendMessage("§c존재하지 않는 명령어입니다.");
        return false;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            for (SubCommand subCommand : subCommandMap.values()) {
                completions.add(subCommand.getName());
            }
        } else {
            SubCommand subCmdInstance = subCommandMap.get(args[0].toLowerCase());
            if (subCmdInstance != null) {
                completions = subCmdInstance.tabComplete(sender, label, args);
            }
        }
        return StringUtil.copyPartialMatches(args[args.length - 1], completions, new ArrayList<>());
    }
}