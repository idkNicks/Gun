package io.github.daawn.gun;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum GunType {

    SIDEARM("보조 무기"),
    SMG("기관단총"),
    SHOTGUN("산탄총"),
    RIFLE("소총"),
    SNIPER("저격소총"),
    MACHINE_GUN("기관단총"),
    MELEE("근접 무기");

    private final String name;
}