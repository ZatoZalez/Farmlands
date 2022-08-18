package me.zato.farmlands.listeners.custom;

import me.zato.farmlands.Farmlands;
import me.zato.farmlands.communication.Logger;
import me.zato.farmlands.economy.shop.ItemData;
import me.zato.farmlands.events.FarmerBuyEvent;
import me.zato.farmlands.farmers.Farmer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class FarmerBuyListener implements Listener {
    public FarmerBuyListener(Farmlands plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    //default items
    @EventHandler
    public void onFarmerBuy(FarmerBuyEvent e){
        Farmer farmer = e.getFarmer();
        ItemData item = e.getItem();

        if(item.isSpecial())
            return;

        if (farmer.hasBalance(item.getPrice())) {
            farmer.withdrawBalance(item.getPrice());
            farmer.giveItem(item.getMaterial(), item.getAmount());
            farmer.sendMessage(item.getName() + "§f has been added to your inventory!");
        } else
            farmer.sendWarning("You do not have enough money to buy §c" + item.getName() + "§6.");
    }

    //farmland
    @EventHandler
    public void onFarmerBuyFarmland(FarmerBuyEvent e){
        Farmer farmer = e.getFarmer();
        ItemData item = e.getItem();

        if(!item.isSpecial())
            return;

        if(!ChatColor.stripColor(item.getName()).toLowerCase().contains("farmland"))
            return;

        if (farmer.hasBalance(item.getPrice())) {
            farmer.withdrawBalance(item.getPrice());
            farmer.addFarmland(item.getAmount());
            farmer.sendMessage("You have bought §6" + item.getAmount() + " §6" + item.getName() + "§f.");
        } else
            farmer.sendWarning("You do not have enough money to buy §c" + item.getAmount() + " §c" + item.getName() + "§6.");
    }
}
