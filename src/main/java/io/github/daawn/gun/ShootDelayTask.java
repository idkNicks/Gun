package io.github.daawn.gun;

import lombok.AllArgsConstructor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
public class ShootDelayTask extends BukkitRunnable {

    private static final Map<String, Long> cooldowns = new HashMap<>();

    private final Gun gun;
    private final Player player;

    @Override
    public void run() {
        cooldowns.remove(getCooldownKey());
    }

    public boolean tryShoot() {
        long now = System.currentTimeMillis();
        String cooldownKey = getCooldownKey();

        if (cooldowns.containsKey(cooldownKey) && cooldowns.get(cooldownKey) > now) {
            long remainingTime = cooldowns.get(cooldownKey) - now;
            double seconds = remainingTime / 1000.0;
            String formattedTime = String.format("%.2f", seconds);
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                    TextComponent.fromLegacyText(
                            ChatColor.GOLD + "[" + ChatColor.WHITE + gun.getName() + ChatColor.GOLD + "] " +
                                    ChatColor.WHITE + "재사용 대기시간: " + formattedTime + "s"
                    ));
            return false;
        }

        cooldowns.put(cooldownKey, now + (long) (gun.getCooldown() * 1000));
        this.runTaskLater(gun.getPlugin(), (long) (gun.getCooldown() * 20));
        return true;
    }

    private String getCooldownKey() {
        return player.getUniqueId() + ":" + gun.getName();
    }
}