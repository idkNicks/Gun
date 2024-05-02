package io.github.daawn.listener;

import io.github.daawn.GunPlugin;
import io.github.daawn.gun.Gun;
import io.github.daawn.gun.GunAction;
import io.github.daawn.gun.GunType;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.UseAnim;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

public class GunShootListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getHand() == EquipmentSlot.HAND && !isSniperRifle(player.getInventory().getItemInMainHand())) {
            Action action = event.getAction();

            Gun gun = getGunFromPlayer(player);
            if (gun != null && gun.getGunType() != GunType.SNIPER) {
                if (GunAction.isShootingAction(action) && attemptToShoot(player, gun)) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onSwapHandItems(PlayerSwapHandItemsEvent event) {
        Player player = event.getPlayer();
        Gun gun = getGunFromPlayer(player);
        if (gun != null && gun.getGunType() == GunType.SNIPER) {
            if (attemptToShoot(player, gun)) {
                event.setCancelled(true);
            }
        }
    }

    private boolean isSniperRifle(ItemStack item) {
        if (isValidGunItem(item)) {
            ItemMeta meta = item.getItemMeta();
            return meta.hasDisplayName() && meta.getDisplayName().toLowerCase().contains("sniper");
        }
        return false;
    }

    private boolean attemptToShoot(Player player, Gun gun) {
        if (gun != null) {
            gun.shoot(player);
            return true;
        }
        return false;
    }

    private Gun getGunFromPlayer(Player player) {
        ItemStack item = player.getInventory().getItemInMainHand();
        if (isValidGunItem(item)) {
            String gunName = item.getItemMeta().getDisplayName();
            return GunPlugin.getInstance().getGunRegistry().getGunByName(gunName);
        }
        return null;
    }

    private boolean isValidGunItem(ItemStack item) {
        return item != null && item.getType() != Material.AIR && item.hasItemMeta() && Objects.requireNonNull(item.getItemMeta()).hasDisplayName();
    }
}