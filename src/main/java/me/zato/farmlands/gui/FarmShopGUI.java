package me.zato.farmlands.gui;

import me.zato.farmlands.communication.Messages;
import me.zato.farmlands.farmers.Farmer;
import me.zato.farmlands.farms.Farms;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class FarmShopGUI {
    private static String title;
    private static Inventory inventory;

    public FarmShopGUI(Farmer farmer) {
        title = ChatColor.GOLD + "Buy your Farm";
        inventory = Bukkit.createInventory(new GUIHolder(GUIHolder.TYPE.FARM_SHOP), 9, title);
        inventory = Inventories.initialize(inventory, farmer, GUIHolder.TYPE.FARM_SHOP);
    }

    public void openInventory(final HumanEntity ent) {
        ent.openInventory(inventory);
    }

    public static void click(final InventoryClickEvent e, Farmer farmer){
        Player p = farmer.getPlayer();


        switch(e.getRawSlot()){
            case 0:
                break;
            case 2: {
                p.closeInventory();

                if(farmer.getFarms().size() >= 5){
                    farmer.sendMessage("You cannot own more than 5 farms.");
                    return;
                }
                Farms.build(farmer);
                break;
            }
            case 8:
                p.closeInventory();
                break;
        }
    }
}
