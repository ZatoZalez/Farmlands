package me.zato.farmlands.listeners.custom;

import me.zato.farmlands.Farmlands;
import me.zato.farmlands.economy.Utils;
import me.zato.farmlands.economy.shop.ItemData;
import me.zato.farmlands.events.FarmerSellEvent;
import me.zato.farmlands.farmers.Farmer;
import me.zato.farmlands.farms.Farm;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class FarmerSellListener implements Listener {
    public FarmerSellListener(Farmlands plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    //default items
    @EventHandler
    public void onFarmerSell(FarmerSellEvent e){
        Farmer farmer = e.getFarmer();
        ItemData item = e.getItem();

        if(item.isSpecial())
            return;

        if (farmer.hasItem(item.getMaterial(), item.getAmount())) {
            farmer.takeItem(item.getMaterial(), item.getAmount());
            farmer.depositBalance(item.getPrice());
            farmer.sendMessage("§6$" + Utils.convertToVisualValue(item.getPrice()) + "§f has been added to your balance!");
        } else
            farmer.sendWarning("You do not have enough §c" + item.getMaterial().toString() + "§6 to sell.");
    }

    //farmland
    @EventHandler
    public void onFarmerSellFarmland(FarmerSellEvent e){
        Farmer farmer = e.getFarmer();
        ItemData item = e.getItem();

        if(!item.isSpecial())
            return;

        if(!ChatColor.stripColor(item.getName()).toLowerCase().contains("farmland"))
            return;

        if (farmer.getFarmland() >= item.getAmount()) {
            farmer.depositBalance(item.getPrice());
            farmer.removeFarmland(item.getAmount());
            farmer.sendMessage("You have sold §6" + item.getAmount() + " §6" + item.getName() + "§f for §6$" + item.getPrice() + "§f.");
        } else
            farmer.sendWarning("You do not have enough §c" + item.getName() + "§6 on your farm to sell.");
    }
}
