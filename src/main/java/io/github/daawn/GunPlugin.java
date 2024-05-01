package io.github.daawn;

import io.github.daawn.command.GunCommand;
import io.github.daawn.command.TestCommand;
import io.github.daawn.listener.GunShootListener;
import io.github.daawn.registry.GunRegistry;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class GunPlugin extends JavaPlugin {

    @Getter
    private static GunPlugin instance;
    private GunRegistry gunRegistry;

    @Override
    public void onEnable() {
        instance = this;

        gunRegistry = new GunRegistry();
        gunRegistry.registerAllGuns();

        new GunCommand(this);
        getCommand("test").setExecutor(new TestCommand());

        getServer().getPluginManager().registerEvents(new GunShootListener(), this);
    }
}