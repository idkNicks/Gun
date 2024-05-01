package io.github.daawn.gun;

import io.github.daawn.GunPlugin;
import io.github.daawn.nbt.NBTItem;
import lombok.Getter;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

@Getter
public abstract class Gun {

    protected final GunPlugin plugin = GunPlugin.getInstance();

    private final String name;
    private final double damage;
    private final double cooldown;

    public Gun(String name, double damage, double cooldown) {
        this.name = name;
        this.damage = damage;
        this.cooldown = cooldown;
    }

    public void shoot(Player player) {
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        NBTItem nbtItem = new NBTItem(itemStack);
        if (nbtItem.hasTag()) {
            String gunName = nbtItem.getString("gunName");
            if (gunName != null && gunName.equals(name)) {
                ShootDelayTask gunTask = new ShootDelayTask(this, player);
                if (gunTask.tryShoot()) {
                    specificShoot(player);
                    applyShootEffects(player);
                    playGunSounds(player, shootSounds());
                }
            }
        }
    }

    protected abstract void specificShoot(Player player);

    private void applyShootEffects(Player player) {
        shootEffects().forEach(effect -> effect.apply(player.getEyeLocation(), player.getEyeLocation().getDirection()));
    }

    protected abstract List<ShootEffect> shootEffects();

    private void playGunSounds(Player player, List<ShootSound> sounds) {
        sounds.forEach(gunSound -> {
            Sound sound = gunSound.getSound();
            String customSound = gunSound.getCustomSound();
            float volume = gunSound.getVolume();
            float pitch = gunSound.getPitch();

            if (sound != null) {
                player.playSound(player.getLocation(), sound, volume, pitch);
            } else if (customSound != null && !customSound.isEmpty()) {
                player.playSound(player.getLocation(), customSound, volume, pitch);
            }
        });
    }

    protected abstract List<ShootSound> shootSounds();

    protected abstract ItemStack createGunItem();

    public ItemStack getGunItem() {
        ItemStack itemStack = createGunItem();
        NBTItem nbtItem = new NBTItem(itemStack);
        nbtItem.setString("gunName", name);
        return nbtItem.getItem();
    }
}