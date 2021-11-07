package lmaxplay.illegalpatch;

import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.event.Listener;
import org.bukkit.material.*;
import org.bukkit.Bukkit;
import org.bukkit.inventory.*;

public class Events implements Listener {
    @EventHandler
    public void onInventory(InventoryEvent event) {
        Inventory inventory = event.getInventory();
        ItemStack[] contents = inventory.getContents();
        for (ItemStack contentitem : contents) {
            Material itemtype = contentitem.getType();
            switch (itemtype) {
                case BEDROCK:
                    inventory.remove(Material.BEDROCK);
                    break;
                case BARRIER:
                    inventory.remove(Material.BARRIER);
                    break;
            }
        }
    }
}
