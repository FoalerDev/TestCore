package cc.foaler.core.utils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Utils {

    public static void copyInventoryToPlayer(Player player, Player target) {
        target.getInventory().setContents(player.getInventory().getContents());

        if(player.getInventory().getHelmet() != null) {
            target.getInventory().setHelmet(player.getInventory().getHelmet());
        }

        if(player.getInventory().getChestplate() != null) {
            target.getInventory().setChestplate(player.getInventory().getChestplate());
        }

        if(player.getInventory().getLeggings() != null) {
            target.getInventory().setLeggings(player.getInventory().getLeggings());
        }

        if(player.getInventory().getBoots() != null) {
            target.getInventory().setBoots(player.getInventory().getBoots());
        }
    }

    public static void putItemsToNumber(Inventory inventory, ItemStack item, int start, int end) {
        for (int i = start; i < end; i++) {
            inventory.setItem(i, item);
        }
    }
}
