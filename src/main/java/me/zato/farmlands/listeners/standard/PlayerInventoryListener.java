package me.zato.farmlands.listeners.standard;

import me.zato.farmlands.Farmlands;
import me.zato.farmlands.farmers.Farmer;
import me.zato.farmlands.farmers.Farmers;
import me.zato.farmlands.gui.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerInventoryListener implements Listener {
    public PlayerInventoryListener(Farmlands plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {
        if(!(e.getInventory().getHolder() instanceof GUIHolder))
            return;

        e.setCancelled(true);
        final ItemStack clickedItem = e.getCurrentItem();
        if (clickedItem == null || clickedItem.getType().isAir()) return;

        final Player p = (Player) e.getWhoClicked();
        Farmer farmer = Farmers.getFarmer(p);
        if(farmer == null)
            return;

        GUIHolder guiHolder = (GUIHolder) e.getInventory().getHolder();
        switch(guiHolder.type){
            case FARMS:
                FarmsGUI.click(e, farmer);
                break;
            case FARM_SHOP:
                FarmShopGUI.click(e, farmer);
                break;
            case FARM_MENU:
                FarmMenuGUI.click(e, farmer);
                break;
            case FARM_RULES:
                FarmRulesGUI.click(e, farmer);
                break;
            case ITEM_SHOP:
                ItemShopGUI.click(e, farmer);
                break;
            case ITEM_BUY:
                ItemBuyGUI.click(e, farmer);
                break;
            case ITEM_SELL:
                ItemSellGUI.click(e, farmer);
                break;
        }
    }

    @EventHandler
    public void onInventoryClose(final InventoryCloseEvent e){
        Player p = (Player)e.getPlayer();
        if(!Farmers.isFarmer(p) || p == null)
            return;

        Farmer farmer = Farmers.getFarmer(p);
        if(!farmer.hasFarm())
            return;

        farmer.setSelectedFarm(null);
    }
}
