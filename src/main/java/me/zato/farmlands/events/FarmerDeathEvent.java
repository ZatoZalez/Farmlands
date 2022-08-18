package me.zato.farmlands.events;

import me.zato.farmlands.farmers.Farmer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.PlayerDeathEvent;

public class FarmerDeathEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    Farmer farmer;
    PlayerDeathEvent event;

    public FarmerDeathEvent(Farmer farmer, PlayerDeathEvent event){
        this.farmer = farmer;
        this.event = event;
    }

    public Farmer getFarmer(){
        return farmer;
    }

    public Player getPlayer(){
        return farmer.getPlayer();
    }

    public void setDeathMessage(String deathMessage){
        event.setDeathMessage(deathMessage);
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
