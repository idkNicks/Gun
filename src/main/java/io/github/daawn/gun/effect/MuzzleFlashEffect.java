package io.github.daawn.gun.effect;

import io.github.daawn.gun.ShootEffect;
import lombok.AllArgsConstructor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.util.Vector;

@AllArgsConstructor
public class MuzzleFlashEffect implements ShootEffect {

    private int count;
    private double offsetX, offsetY, offsetZ, speed;

    @Override
    public void apply(Location location, Vector direction) {
        location.getWorld().spawnParticle(Particle.FLAME, location, count, offsetX, offsetY, offsetZ, speed);
    }
}