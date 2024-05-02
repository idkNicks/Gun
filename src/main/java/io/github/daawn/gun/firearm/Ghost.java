package io.github.daawn.gun.firearm;

import io.github.daawn.builder.ItemBuilder;
import io.github.daawn.gun.*;
import io.github.daawn.gun.effect.TrailEffect;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

@RegisterGun
public class Ghost extends Gun {

    public Ghost() {
        super(GunType.SIDEARM, "고스트", 8, 0.35);
    }

    @Override
    protected void specificShoot(Player player) {
    }

    @Override
    protected List<ShootEffect> shootEffects() {
        return List.of(new TrailEffect(Particle.CRIT_MAGIC, 30, 8.0, 70.0));
    }

    @Override
    protected List<ShootSound> shootSounds() {
        return List.of(
                new ShootSound(Sound.ITEM_TRIDENT_RIPTIDE_1, 2F, 1.5F));
    }

    @Override
    protected ItemStack createGunItem() {
        return new ItemBuilder(Material.LEATHER_HORSE_ARMOR)
                .setName("고스트")
                .build();
    }
}
