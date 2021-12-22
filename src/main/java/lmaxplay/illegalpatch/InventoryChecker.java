package lmaxplay.illegalpatch;

import lmaxplay.illegalpatch.IllegalPatch;

import net.kyori.adventure.text.Component;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.*;
import org.bukkit.Bukkit;
import org.bukkit.inventory.*;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.configuration.Configuration;
import org.bukkit.inventory.meta.*;

import com.comphenix.protocol.utility.*;
import com.comphenix.protocol.*;
import com.comphenix.protocol.wrappers.nbt.*;

import java.io.ObjectInputFilter;
import java.util.*;
import java.util.logging.Logger;

public class InventoryChecker extends BukkitRunnable {
    //@SuppressWarnings("deprecation")
    @Override
    public void run(){
        Configuration config = IllegalPatch.config;
        List<Integer> toRemove = new ArrayList<>(); //Removal should be sync
        List<String> StringIllegalsNonOp = config.getStringList("illegals.non-operator");
        List<String> StringIllegalsNormal = config.getStringList("illegals.all");
        List<Material> IllegalsNonOp = new ArrayList<>();
        for(String str : StringIllegalsNonOp) {
            try {
                Material material = Material.matchMaterial(str);
                if(material != null) {
                    IllegalsNonOp.add(material);
                }
            } catch (Exception exception) {
                Bukkit.getLogger().warning("Unable to parse config illegals.non-operator");
            }
        }
        List<Material> IllegalsNormal = new ArrayList<>();
        for(String str : StringIllegalsNormal) {
            try {
                Material material = Material.matchMaterial(str);
                if(material != null) {
                    IllegalsNormal.add(material);
                }
            } catch (Exception exception) {
                Bukkit.getLogger().warning("Unable to parse config illegals.all");
            }
        }

        for(Player player : Bukkit.getOnlinePlayers()) {

            Inventory playerInventory = player.getInventory();

            for (int i = 0; i < playerInventory.getContents().length; i++) {
                if(playerInventory.getContents()[i] == null) {
                    continue;
                }
                ItemStack content = playerInventory.getContents()[i];
                if(!player.hasPermission("illegalpatch.bypasscheck.stacksize")) {
                    if (content.getAmount() >= content.getType().getMaxStackSize()) {
                        content.setAmount(content.getType().getMaxStackSize());
                    }
                }

                if(!player.hasPermission("illegalpatch.bypasscheck.enchantments")) {
                    for (Map.Entry<Enchantment, Integer> entry : content.getEnchantments().entrySet()) {
                        if (entry.getKey().getMaxLevel() >= entry.getValue()) {
                            content.removeEnchantment(entry.getKey());
                            content.addEnchantment(entry.getKey(), entry.getKey().getMaxLevel());
                        }
                    }
                }

                if(!player.hasPermission("illegalpatch.bypasscheck.items")) {
                    if (IllegalsNormal.contains(content.getType())) toRemove.add(i);
                    else if (IllegalsNonOp.contains(content.getType()) && !player.isOp()) toRemove.add(i);
                }

                if(content.getAmount() >= 128 || content.getAmount() <= -1) {
                    player.kick(Component.text("Stack size invalid, terminating client"));
                }
            }
            Bukkit.getScheduler().runTask(IllegalPatch.instance, () ->
            {
                for(Integer content : toRemove)
                    playerInventory.setItem(content, null);
            });
        }
    }
}