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
public class Operator extends Gun {

    public Operator() {
        super(GunType.SNIPER,"오퍼레이터", 20, 3);
    }

    @Override
    protected void specificShoot(Player player) {
    }

    @Override
    protected List<ShootEffect> shootEffects() {
        return List.of(
                new TrailEffect(Particle.FLAME, 30, 8.0, 70.0),
                new TrailEffect(Particle.FIREWORKS_SPARK, 30, 8.0, 70.0),
                new TrailEffect(Particle.ASH, 3, 5.0, 70.0),
                new TrailEffect(Particle.WHITE_ASH, 1, 1, 3.0),
                new MuzzleFlashEffect(10, 0.2, 0.2, 0.2, 0.1),
                new SmokeEffect(10, 0.2, 0.2, 0.2, 0.1)
        );
    }

    @Override
    protected List<ShootSound> shootSounds() {
        return List.of(
                new ShootSound(Sound.ENTITY_GENERIC_EXPLODE, 2f, 1.5f)
        );
    }

    @Override
    protected ItemStack createGunItem() {
        return new ItemBuilder(Material.SPYGLASS)
                .setName("오퍼레이터")
                .build();
    }
}
