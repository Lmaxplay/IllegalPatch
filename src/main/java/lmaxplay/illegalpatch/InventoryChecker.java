package lmaxplay.illegalpatch;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.material.*;
import org.bukkit.Bukkit;
import org.bukkit.inventory.*;
import org.bukkit.plugin.Plugin;
import org.bukkit.inventory.InventoryView;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.configuration.Configuration;

import java.util.Collection;


public class InventoryChecker extends BukkitRunnable {
    @Override
    public void run(){
        for(Player player : Bukkit.getOnlinePlayers()) {
            Inventory playerInventory = player.getInventory();
        }
    }
}