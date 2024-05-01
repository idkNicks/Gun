package io.github.daawn.gun;

import lombok.Getter;
import org.bukkit.Sound;

@Getter
public class ShootSound {

    private Sound sound;
    private String customSound;
    private final float volume;
    private final float pitch;

    public ShootSound(Sound sound, float volume, float pitch) {
        this.sound = sound;
        this.volume = volume;
        this.pitch = pitch;
    }

    public ShootSound(String customSound, float volume, float pitch) {
        this.customSound = customSound;
        this.volume = volume;
        this.pitch = pitch;
    }
}