package io.github.daawn.gun.effect;

import io.github.daawn.GunPlugin;
import io.github.daawn.gun.ShootEffect;
import lombok.AllArgsConstructor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

@AllArgsConstructor
public class TrailEffect implements ShootEffect {

    private final Particle particle;
    private final int count;
    private final double distanceBetween;
    private final double range;

    @Override
    public void apply(Location location, Vector direction) {
        direction.normalize();
        Location currentLocation = location.clone();

        new BukkitRunnable() {
            private double traveled = 0.0;

            @Override
            public void run() {
                if (traveled < range) {
                    currentLocation.add(direction.clone().multiply(distanceBetween));
                    location.getWorld().spawnParticle(particle, currentLocation, count, 0, 0, 0, 0, null, true);
                    traveled += distanceBetween;
                } else {
                    this.cancel();
                }
            }
        }.runTaskTimer(GunPlugin.getInstance(), 0L, 1L);
    }
}