package me.serbob.toastedkilldeath;

import me.serbob.toastedkilldeath.Commands.SetKillDeath;
import me.serbob.toastedkilldeath.TabCompleters.SetKillDeathTabCompleter;
import org.bukkit.plugin.java.JavaPlugin;

public final class ToastedKillDeath extends JavaPlugin {
    public static ToastedKillDeath instance;
    @Override
    public void onEnable() {
        instance=this;
        getCommand("tset").setExecutor(new SetKillDeath());
        getCommand("tset").setTabCompleter(new SetKillDeathTabCompleter());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
