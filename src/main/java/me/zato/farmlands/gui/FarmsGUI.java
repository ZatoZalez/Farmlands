package me.zato.farmlands.gui;

import me.zato.farmlands.commands.ChatMessage;
import me.zato.farmlands.communication.Messages;
import me.zato.farmlands.economy.shop.ItemData;
import me.zato.farmlands.economy.shop.Items;
import me.zato.farmlands.events.FarmerSellEvent;
import me.zato.farmlands.farmers.Farmer;
import me.zato.farmlands.farmers.Farmers;
import me.zato.farmlands.farms.Farm;
import me.zato.farmlands.farms.Farms;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class FarmsGUI {
    private static String title;
    private static Inventory inventory;

    public FarmsGUI(Farmer farmer) {
        title = ChatColor.GOLD + farmer.getPlayer().getName();
        inventory = Bukkit.createInventory(new GUIHolder(GUIHolder.TYPE.FARMS), 9, title);
        inventory = Inventories.initialize(inventory, farmer, GUIHolder.TYPE.FARMS);
    }

    public void openInventory(final HumanEntity ent) {
        ent.openInventory(inventory);
    }

    public static void click(final InventoryClickEvent e, Farmer farmer){
        if(e.getCurrentItem() == null)
            return;

        Player p = farmer.getPlayer();
        if(e.getRawSlot() == 0){
            Farm farm = farmer.getSelectedFarm();
            p.closeInventory();
            farmer.setSelectedFarm(farm);
            FarmShopGUI farmShopGUI = new FarmShopGUI(farmer);
            farmShopGUI.openInventory(p);
            return;
        }

        if(e.getRawSlot() == 8){
            p.closeInventory();
            return;
        }

        Farm farm = Farms.getFarm(ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()));
        if(farm != null){
            p.closeInventory();
            if(e.getClick() == ClickType.RIGHT) {
                farmer.sendMessage(Messages.player_teleport_farm);
                farmer.getPlayer().teleport(farmer.getDefaultOrCurrentFarm().getHome());
                return;
            }
            farmer.setSelectedFarm(farm);
            FarmMenuGUI farmMenuGUI = new FarmMenuGUI(farmer);
            farmMenuGUI.openInventory(farmer.getPlayer());
            return;
        }
    }
}
