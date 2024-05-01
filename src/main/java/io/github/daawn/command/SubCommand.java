package io.github.daawn.command;

import org.bukkit.command.CommandSender;

import java.util.List;

public interface SubCommand {

    String getName();

    String getDescription();

    String getUsage();

    boolean hasPermission(CommandSender sender);

    void execute(CommandSender sender, String label, String[] args);

    List<String> tabComplete(CommandSender sender, String label, String[] args);
}