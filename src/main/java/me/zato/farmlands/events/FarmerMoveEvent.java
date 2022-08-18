package me.zato.farmlands.events;

import me.zato.farmlands.farmers.Farmer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerMoveEvent;

public class FarmerMoveEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    Farmer farmer;
    PlayerMoveEvent event;

    public FarmerMoveEvent(Farmer farmer, PlayerMoveEvent event){
        this.farmer = farmer;
        this.event = event;
    }

    public Farmer getFarmer(){
        return farmer;
    }

    public Player getPlayer(){
        return farmer.getPlayer();
    }

    public void setCancelled(boolean cancel){
        event.setCancelled(cancel);
    }

    public HandlerList getHandlers(){
        return handlers;
    }

    public static HandlerList getHandlerList(){
        return handlers;
    }
}
