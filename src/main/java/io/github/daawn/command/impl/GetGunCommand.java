package io.github.daawn.command.impl;

import io.github.daawn.GunPlugin;
import io.github.daawn.command.SubCommand;
import io.github.daawn.gun.Gun;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class GetGunCommand implements SubCommand {

    @Override
    public String getName() {
        return "지급";
    }

    @Override
    public String getDescription() {
        return "<이름>의 총을 지급 받습니다.";
    }

    @Override
    public String getUsage() {
        return "<이름>";
    }

    @Override
    public boolean hasPermission(CommandSender sender) {
        return sender.hasPermission("gun.admin.get");
    }

    @Override
    public void execute(CommandSender sender, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("이 명령어는 플레이어만 사용할 수 있습니다.");
            return;
        }

        if (args.length == 1) {
            player.sendMessage(GunPlugin.getInstance().getGunRegistry().getAllGunNames() + "");
            return;
        }

        String gunName = args[1];
        Gun gun = GunPlugin.getInstance().getGunRegistry().getGunByName(gunName);

        if (gun == null) {
            player.sendMessage("해당 이름의 총이 존재하지 않습니다.");
            return;
        }

        player.getInventory().addItem(gun.getGunItem());
        player.sendMessage(gunName + "을(를) 받았습니다.");
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String label, String[] args) {
        if (args.length == 2) {
            return GunPlugin.getInstance().getGunRegistry().getAllGunNames();
        }
        return Collections.emptyList();
    }
}