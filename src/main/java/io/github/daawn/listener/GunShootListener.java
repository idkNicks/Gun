package io.github.daawn.listener;

import io.github.daawn.GunPlugin;
import io.github.daawn.gun.Gun;
import io.github.daawn.gun.GunAction;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

public class GunShootListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getHand() == EquipmentSlot.HAND) {
            Player player = event.getPlayer();
            Action action = event.getAction();

            if (GunAction.isShootingAction(action)) {
                if (attemptToShoot(player)) {
                    event.setCancelled(true);
                }
            }
        }
    }

    private boolean attemptToShoot(Player player) {
        ItemStack itemInHand = player.getInventory().getItemInMainHand();
        if (isValidGunItem(itemInHand)) {
            ItemMeta itemMeta = itemInHand.getItemMeta();
            if (itemMeta != null && itemMeta.hasDisplayName()) {
                String gunName = itemMeta.getDisplayName();
                Gun gun = GunPlugin.getInstance().getGunRegistry().getGunByName(gunName);
                if (gun != null) {
                    gun.shoot(player);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isValidGunItem(ItemStack item) {
        return item != null && item.getType() != Material.AIR && item.hasItemMeta() && Objects.requireNonNull(item.getItemMeta()).hasDisplayName();
    }
}