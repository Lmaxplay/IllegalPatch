package lmaxplay.illegalpatch;

import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Server;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.PluginLogger;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.logging.Logger;

import lmaxplay.illegalpatch.InventoryChecker;

public final class IllegalPatch extends JavaPlugin {
    public Configuration config;
    @Override
    public void onEnable() {
        // Plugin startup logic
        config = this.getConfig();
        Server server = Bukkit.getServer();
        PluginManager manager = Bukkit.getPluginManager();
        BukkitScheduler scheduler = Bukkit.getScheduler();
        new InventoryChecker().runTaskTimer(this, 0L, 1L);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
