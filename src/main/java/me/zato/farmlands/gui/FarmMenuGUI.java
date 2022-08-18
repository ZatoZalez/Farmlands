package me.zato.farmlands.gui;

import me.zato.farmlands.commands.ChatMessage;
import me.zato.farmlands.communication.Messages;
import me.zato.farmlands.farmers.Farmer;
import me.zato.farmlands.farmers.Farmers;
import me.zato.farmlands.farms.Farm;
import me.zato.farmlands.farms.Farms;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class FarmMenuGUI {
    private static String title;

    private static Inventory inventory;

    public FarmMenuGUI(Farmer farmer) {
        title = ChatColor.GOLD + farmer.getSelectedFarm().getName();
        inventory = Bukkit.createInventory(new GUIHolder(GUIHolder.TYPE.FARM_MENU), 27, title);
        inventory = Inventories.initialize(inventory, farmer, GUIHolder.TYPE.FARM_MENU);
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
                FarmsGUI farmsGUI = new FarmsGUI(farmer);
                farmsGUI.openInventory(farmer.getPlayer());
                break;
            }
            case 4: {
                Farm farm = farmer.getSelectedFarm();
                p.closeInventory();
                farmer.sendMessage(Messages.player_teleport_farm);
                farmer.getPlayer().teleport(farm.getHome());
                break;
            }
            case 19: {
                Farm farm = farmer.getSelectedFarm();
                p.closeInventory();
                ItemShopGUI itemShopGUI = new ItemShopGUI(farmer);
                itemShopGUI.openInventory(farmer.getPlayer());
                break;
            }
            case 21: {
                Farm farm = farmer.getSelectedFarm();
                p.closeInventory();
                farmer.setSelectedFarm(farm);
                FarmRulesGUI farmRulesGUI = new FarmRulesGUI(farmer);
                farmRulesGUI.openInventory(farmer.getPlayer());
                break;
            }
            case 23:{
                Farm farm = farmer.getSelectedFarm();
                p.closeInventory();
                farmer.setSelectedFarm(farm);
                ChatMessage.addAwaitingMessage(farmer.getPlayer(), ChatMessage.TYPE.RENAME);
                farmer.sendMessage(Messages.player_rename_through_chat);
                break;
            }
            case 25:{
                Farm farm = farmer.getSelectedOrCurrentFarm();
                p.closeInventory();
                if(farm == null){
                    farmer.sendMessage("Could not delete farm due to an error...");
                    return;
                }
                farmer.sendMessage("Deleting " + farm.getName() + "...");
                farm.permanentlyDelete();
                farmer.sendMessage("Deleted " + farm.getName() + ".");
                break;
            }
            case 8:
                p.closeInventory();
                break;
        }
    }
}
