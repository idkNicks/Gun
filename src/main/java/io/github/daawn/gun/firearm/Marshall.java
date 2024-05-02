package io.github.daawn.gun.firearm;

import io.github.daawn.builder.ItemBuilder;
import io.github.daawn.gun.*;
import io.github.daawn.gun.effect.MuzzleFlashEffect;
import io.github.daawn.gun.effect.SmokeEffect;
import io.github.daawn.gun.effect.TrailEffect;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

@RegisterGun
public class Marshall extends Gun {

    public Marshall() {
        super(GunType.SNIPER, "마샬", 10.0, 1);
    }

    @Override
    public void specificShoot(Player player) {
    }

    @Override
    public List<ShootEffect> shootEffects() {
        return List.of(
                new TrailEffect(Particle.CRIT_MAGIC, 30, 8.0, 70.0),
                new TrailEffect(Particle.ASH, 3, 5.0, 70.0),
                new TrailEffect(Particle.FLAME, 1, 1, 3.0),
                new MuzzleFlashEffect(10, 0.2, 0.2, 0.2, 0.1),
                new SmokeEffect(5, 0.2, 0.2, 0.2, 0.1)
        );
    }

    @Override
    protected List<ShootSound> shootSounds() {
        return List.of(
                new ShootSound(Sound.ITEM_TRIDENT_RIPTIDE_1, 2F, 1.5F),
                new ShootSound(Sound.ENTITY_GENERIC_EXPLODE, 0.5F, 3F)
        );
    }

    @Override
    protected ItemStack createGunItem() {
        return new ItemBuilder(Material.SPYGLASS)
                .setName("마샬")
                .build();
    }
}