package lmaxplay.illegalpatch;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Server;
import org.bukkit.plugin.PluginManager;
import lmaxplay.illegalpatch.Events;
import org.bukkit.plugin.PluginLogger;
import java.util.logging.Logger;

public final class IllegalPatch extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Server server = Bukkit.getServer();
        PluginManager manager = Bukkit.getPluginManager();
        Logger logger = Bukkit.getLogger();
        manager.registerEvents(new Events(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
