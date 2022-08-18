package me.zato.farmlands.gui;

import me.zato.farmlands.economy.shop.ItemData;
import me.zato.farmlands.economy.shop.Items;
import me.zato.farmlands.events.FarmerSellEvent;
import me.zato.farmlands.farmers.Farmer;
import me.zato.farmlands.farmers.Farmers;
import me.zato.farmlands.farms.Farm;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;


public class ItemSellGUI {
    private static String title;
    private static Inventory inventory;

    public ItemSellGUI(Farmer farmer) {
        title = ChatColor.GOLD + "Sell items";
        inventory = Bukkit.createInventory(new GUIHolder(GUIHolder.TYPE.ITEM_SELL), 36, title);
        inventory = Inventories.initialize(inventory, farmer, GUIHolder.TYPE.ITEM_SELL);
    }

    public void openInventory(final HumanEntity ent) {
        ent.openInventory(inventory);
    }

    public static void click(final InventoryClickEvent e, Farmer farmer){
        Player p = farmer.getPlayer();
        if(e.getCurrentItem() == null)
            return;

        ItemData item = Items.getItem(ItemData.ItemType.SELLABLE, e.getCurrentItem().getItemMeta().getDisplayName());
        if(item != null){
            if(Farmers.isFarmer(p))
                Bukkit.getServer().getPluginManager().callEvent(new FarmerSellEvent(Farmers.getFarmer(p), item));
            return;
        }

        if(e.getRawSlot() == 0){
            Farm farm = farmer.getSelectedFarm();
            p.closeInventory();
            farmer.setSelectedFarm(farm);
            ItemShopGUI itemShopGUI = new ItemShopGUI(farmer);
            itemShopGUI.openInventory(farmer.getPlayer());
            return;
        }

        if(e.getRawSlot() == 8){
            p.closeInventory();
            return;
        }
    }
}

