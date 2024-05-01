package io.github.daawn.gun.effect;

import io.github.daawn.gun.ShootEffect;
import lombok.AllArgsConstructor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.util.Vector;

@AllArgsConstructor
public class SmokeEffect implements ShootEffect {

    private int count;
    private double offsetX, offsetY, offsetZ, speed;

    public void apply(Location location, Vector direction) {
        location.getWorld().spawnParticle(Particle.SMOKE_LARGE, location, count, offsetX, offsetY, offsetZ, speed);
    }
}