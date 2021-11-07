package lmaxplay.illegalpatch;

import lmaxplay.illegalpatch.IllegalPatch;

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

import java.io.ObjectInputFilter;
import java.util.*;


public class InventoryChecker extends BukkitRunnable {
    @Override
    public void run(){
        Configuration config = IllegalPatch.config;
        List<Integer> toRemove = new ArrayList<>(); //Removal should be sync
        List<String> StringIllegalsNonOp = config.getStringList("illegals.non-operator");
        List<String> StringIllegalsNormal = config.getStringList("illegals.all");


        for(Player player : Bukkit.getOnlinePlayers()) {

            Inventory playerInventory = player.getInventory();

            for (int i = 0; i < playerInventory.getContents().length; i++) {
                ItemStack content = playerInventory.getContents()[i];

                if (content.getType() == Material.BEDROCK)
                    toRemove.add(i);
            }
            Bukkit.getScheduler().runTask(IllegalPatch.instance, () ->
            {
                for(Integer content : toRemove)
                    playerInventory.setItem(content, null);
            });
        }
    }
}