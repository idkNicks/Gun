package io.github.daawn.registry;

import io.github.daawn.GunPlugin;
import io.github.daawn.gun.Gun;
import io.github.daawn.gun.RegisterGun;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class GunRegistry {

    private final Map<String, Gun> gunMap = new HashMap<>();

    public void registerAllGuns() {
        Reflections reflections = new Reflections("io.github.daawn.gun.firearm");
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(RegisterGun.class);

        annotated.forEach(gunClass -> {
            if (Gun.class.isAssignableFrom(gunClass)) {
                try {
                    Gun gun = (Gun) gunClass.getDeclaredConstructor().newInstance();
                    gunMap.put(gun.getName(), gun);
                } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                         InvocationTargetException e) {
                    GunPlugin.getInstance().getLogger().warning("총을 등록하는 과정에 오류가 발생하였습니다. (관리자에게 문의해 주세요.)");
                }
            }
        });
    }

    public Gun getGunByName(String name) {
        return gunMap.get(name);
    }

    public List<String> getAllGunNames() {
        return gunMap.values().stream()
                .map(Gun::getName)
                .collect(Collectors.toList());
    }
}