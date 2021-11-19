package lmaxplay.illegalpatch;

import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Server;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.PluginLogger;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

import java.util.logging.Logger;

import lmaxplay.illegalpatch.InventoryChecker;

public final class IllegalPatch extends JavaPlugin {
    public static Configuration config;
    public static JavaPlugin instance;
    private BukkitTask checkertask;
    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        saveDefaultConfig();
        config = this.getConfig();
        Server server = Bukkit.getServer();
        PluginManager manager = Bukkit.getPluginManager();
        BukkitScheduler scheduler = Bukkit.getScheduler();
        checkertask = new InventoryChecker().runTaskTimerAsynchronously(this, 0L, 1L);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Shutting down IllegalPatch");
        checkertask.cancel();
    }

}
