package me.zato.farmlands.events;

import me.zato.farmlands.economy.shop.ItemData;
import me.zato.farmlands.farmers.Farmer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class FarmerSellEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    Farmer farmer;
    ItemData item;

    public FarmerSellEvent(Farmer farmer, ItemData item){
        this.farmer = farmer;
        this.item = item;
    }

    public Farmer getFarmer(){
        return farmer;
    }

    public Player getPlayer(){
        return farmer.getPlayer();
    }

    public ItemData getItem(){
        return item;
    }

    public HandlerList getHandlers(){
        return handlers;
    }

    public static HandlerList getHandlerList(){
        return handlers;
    }
}