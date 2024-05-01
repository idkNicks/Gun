package io.github.daawn.gun;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.event.block.Action;

import java.util.EnumSet;

@AllArgsConstructor
@Getter
public enum GunAction {

    SHOOTING(EnumSet.of(Action.RIGHT_CLICK_AIR, Action.RIGHT_CLICK_BLOCK));

    private final EnumSet<Action> actions;

    public static boolean isShootingAction(Action action) {
        for (GunAction gunAction : GunAction.values()) {
            if (gunAction.actions.contains(action)) {
                return true;
            }
        }
        return false;
    }
}