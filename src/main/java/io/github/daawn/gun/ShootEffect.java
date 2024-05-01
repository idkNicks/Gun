package io.github.daawn.gun;

import org.bukkit.Location;
import org.bukkit.util.Vector;

public interface ShootEffect {

    void apply(Location location, Vector direction);
}