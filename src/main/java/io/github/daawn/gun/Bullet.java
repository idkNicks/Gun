package io.github.daawn.gun;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;

import java.util.Objects;

public class Bullet {

    private final Player shooter;
    private final Vector direction;
    private final double range;
    private final double damage;

    public Bullet(Player shooter, double damage, double range) {
        this.shooter = shooter;
        this.direction = shooter.getEyeLocation().getDirection();
        this.range = range;
        this.damage = damage;
    }

    public void fire() {
        Location startLocation = shooter.getEyeLocation();
        Vector increment = direction.normalize().multiply(0.5);
        double traveledDistance = 0;

        while (traveledDistance < range) {
            startLocation.add(increment);
            traveledDistance += 0.5;
            Entity hitEntity = checkCollision(startLocation);
            if (hitEntity != null) {
                applyEffects(startLocation);
                if (hitEntity instanceof LivingEntity) {
                    ((LivingEntity) hitEntity).damage(damage, shooter);
                }
                break;
            }
        }
    }

    private Entity checkCollision(Location location) {
        BoundingBox bulletBox = BoundingBox.of(location, 0, 0, 0);
        for (Entity entity : Objects.requireNonNull(location.getWorld()).getNearbyEntities(bulletBox)) {
            if (entity instanceof LivingEntity target && entity != shooter) {
                if (target.getBoundingBox().overlaps(bulletBox)) {
                    return entity;
                }
            }
        }
        return null;
    }

    private void applyEffects(Location hitLocation) {
        hitLocation.getWorld().spawnParticle(Particle.FLAME, hitLocation, 10, 0.2, 0.2, 0.2, 0.02);
    }
}