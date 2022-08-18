package me.zato.farmlands.gui;

import me.zato.farmlands.farmers.Farmer;
import me.zato.farmlands.farms.Farm;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;


public class ItemShopGUI {

    private static String title;
    private static Inventory inventory;

    public ItemShopGUI(Farmer farmer) {
        title = ChatColor.GOLD + "Item shop";
        inventory = Bukkit.createInventory(new GUIHolder(GUIHolder.TYPE.ITEM_SHOP), 9, title);
        inventory = Inventories.initialize(inventory, farmer, GUIHolder.TYPE.ITEM_SHOP);
    }

    public void openInventory(final HumanEntity ent) {
        ent.openInventory(inventory);
    }

    public static void click(final InventoryClickEvent e, Farmer farmer){
        Player p = farmer.getPlayer();
        switch(e.getRawSlot()){
            case 0:{
                Farm farm = farmer.getSelectedFarm();
                p.closeInventory();
                farmer.setSelectedFarm(farm);
                FarmMenuGUI farmStatsGUI = new FarmMenuGUI(farmer);
                farmStatsGUI.openInventory(farmer.getPlayer());
            }
            break;
            case 3:{
                Farm farm = farmer.getSelectedFarm();
                p.closeInventory();
                farmer.setSelectedFarm(farm);
                ItemBuyGUI itemBuyGUI = new ItemBuyGUI(farmer);
                itemBuyGUI.openInventory(farmer.getPlayer());
            }
            break;
            case 5:{
                Farm farm = farmer.getSelectedFarm();
                p.closeInventory();
                farmer.setSelectedFarm(farm);
                ItemSellGUI itemSellGUI = new ItemSellGUI(farmer);
                itemSellGUI.openInventory(farmer.getPlayer());
            }
            break;
            case 8:
                p.closeInventory();
                break;

        }
    }
}
