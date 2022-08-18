package me.zato.farmlands.gui;

import me.zato.farmlands.farmers.Farmer;
import me.zato.farmlands.farms.Farm;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameRule;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class FarmRulesGUI {
    private static String title;
    private static Inventory inventory;

    public FarmRulesGUI(Farmer farmer) {
        title = ChatColor.GOLD + "Farm Rules";
        inventory = Bukkit.createInventory(new GUIHolder(GUIHolder.TYPE.FARM_RULES), 9, title);
        inventory = Inventories.initialize(inventory, farmer, GUIHolder.TYPE.FARM_RULES);
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
                break;
            }
            case 3: {
                Farm farm = farmer.getSelectedFarm();
                p.closeInventory();
                if(e.getCurrentItem().getType().equals(Material.GRAY_DYE)){ //ENABLE MOB SPAWNING
                    farm.getWorld().setGameRule(GameRule.DO_MOB_SPAWNING, true);
                    farmer.sendMessage("You successfully enabled Mob Spawning on §6" + farm.getName() + "§f.");
                }
                else if(e.getCurrentItem().getType().equals(Material.LIME_DYE)){ //DISABLE MOB SPAWNING
                    farm.getWorld().setGameRule(GameRule.DO_MOB_SPAWNING, false);
                    farmer.sendMessage("You successfully disabled Mob Spawning on §6" + farm.getName() + "§f.");
                }
                break;
            }
            case 4: {
                Farm farm = farmer.getSelectedFarm();
                p.closeInventory();
                if(farm.getWarp() == null){
                    farmer.sendMessage("You have to set a warp on §6" + farm.getName() + "§f before publishing. Use §6/farm setwarp§f to set a warp.");
                    p.closeInventory();
                    return;
                }

                if(e.getCurrentItem().getType().equals(Material.GRAY_DYE)){ //PUBLISH FARM
                    farm.isPublic(true);
                    farmer.sendMessage("You successfully published §6" + farm.getName() + "§f for others.");
                }
                else if(e.getCurrentItem().getType().equals(Material.LIME_DYE)){ //PRIVATIZE FARM
                    farm.isPublic(false);
                    farmer.sendMessage("You successfully privatized §6" + farm.getName() + "§f for others.");
                }
                break;
            }
            case 8:
                p.closeInventory();
                break;

        }
    }
}
