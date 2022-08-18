package me.zato.farmlands.events;

import io.papermc.paper.event.entity.EntityDamageItemEvent;
import me.zato.farmlands.farmers.Farmer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class FarmerInteractEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    public enum InteractType{
        BLOCK_BREAK,
        BLOCK_PLACE,
        BLOCK_INTERACT,
        ENTITY_DAMAGE,
        ENTITY_INTERACT
    }

    public enum EventType{
        BLOCK_BREAK_EVENT,
        BLOCK_PLACE_EVENT,
        ENTITY_CHANGE_BLOCK_EVENT,
        PLAYER_INTERACT_EVENT,
        PLAYER_INTERACT_ENTITY_EVENT,
        ENTITY_DAMAGE_BY_ENTITY_EVENT,
        HANGING_BREAK_BY_ENTITY_EVENT,
        PLAYER_ARMOR_STAND_MANIPULATE_EVENT,
        ENTITY_DAMAGE_ITEM_EVENT
    }

    BlockBreakEvent blockBreakEvent;
    BlockPlaceEvent blockPlaceEvent;
    EntityChangeBlockEvent entityChangeBlockEvent;
    PlayerInteractEvent playerInteractEvent;
    PlayerInteractEntityEvent playerInteractEntityEvent;
    EntityDamageByEntityEvent entityDamageByEntityEvent;
    PlayerArmorStandManipulateEvent playerArmorStandManipulateEvent;
    EntityDamageItemEvent entityDamageItemEvent;
    HangingBreakByEntityEvent hangingBreakByEntityEvent;

    Farmer farmer;
    InteractType interactType;
    EventType eventType;

    public FarmerInteractEvent(Farmer farmer, BlockBreakEvent event){ this.farmer = farmer; this.blockBreakEvent = event; interactType = InteractType.BLOCK_BREAK; eventType = EventType.BLOCK_BREAK_EVENT; }
    public FarmerInteractEvent(Farmer farmer, BlockPlaceEvent event){ this.farmer = farmer; this.blockPlaceEvent = event; interactType = InteractType.BLOCK_PLACE; eventType = EventType.BLOCK_PLACE_EVENT; }
    public FarmerInteractEvent(Farmer farmer, EntityChangeBlockEvent event){ this.farmer = farmer; this.entityChangeBlockEvent = event; interactType = InteractType.BLOCK_INTERACT; eventType = EventType.ENTITY_CHANGE_BLOCK_EVENT; }
    public FarmerInteractEvent(Farmer farmer, PlayerInteractEvent event){ this.farmer = farmer; this.playerInteractEvent = event; interactType = InteractType.BLOCK_INTERACT; eventType = EventType.PLAYER_INTERACT_EVENT; }
    public FarmerInteractEvent(Farmer farmer, PlayerInteractEntityEvent event){ this.farmer = farmer; this.playerInteractEntityEvent = event; interactType = InteractType.ENTITY_INTERACT; eventType = EventType.PLAYER_INTERACT_ENTITY_EVENT; }
    public FarmerInteractEvent(Farmer farmer, EntityDamageByEntityEvent event){ this.farmer = farmer; this.entityDamageByEntityEvent = event; interactType = InteractType.ENTITY_DAMAGE; eventType = EventType.ENTITY_DAMAGE_BY_ENTITY_EVENT; }
    public FarmerInteractEvent(Farmer farmer, HangingBreakByEntityEvent event){ this.farmer = farmer; this.hangingBreakByEntityEvent = event; interactType = InteractType.ENTITY_INTERACT; eventType = EventType.HANGING_BREAK_BY_ENTITY_EVENT; }
    public FarmerInteractEvent(Farmer farmer, PlayerArmorStandManipulateEvent event){ this.farmer = farmer; this.playerArmorStandManipulateEvent = event; interactType = InteractType.BLOCK_INTERACT; eventType = EventType.PLAYER_ARMOR_STAND_MANIPULATE_EVENT; }
    public FarmerInteractEvent(Farmer farmer, EntityDamageItemEvent event){ this.farmer = farmer; this.entityDamageItemEvent = event; interactType = InteractType.BLOCK_INTERACT; eventType = EventType.ENTITY_DAMAGE_ITEM_EVENT; }


    public Farmer getFarmer(){
        return farmer;
    }

    public Player getPlayer(){
        return farmer.getPlayer();
    }

    public InteractType getInteractType(){
        return interactType;
    }

    public EventType getEventType(){
        return eventType;
    }

    public BlockBreakEvent getBlockBreakEvent() {
        return blockBreakEvent;
    }

    public BlockPlaceEvent getBlockPlaceEvent() {
        return blockPlaceEvent;
    }

    public EntityChangeBlockEvent getEntityChangeBlockEvent() {
        return entityChangeBlockEvent;
    }

    public PlayerInteractEvent getPlayerInteractEvent() {
        return playerInteractEvent;
    }

    public PlayerInteractEntityEvent getPlayerInteractEntityEvent() {
        return playerInteractEntityEvent;
    }

    public EntityDamageByEntityEvent getEntityDamageByEntityEvent() {
        return entityDamageByEntityEvent;
    }

    public HangingBreakByEntityEvent getHangingBreakByEntityEvent() {
        return hangingBreakByEntityEvent;
    }

    public PlayerArmorStandManipulateEvent getPlayerArmorStandManipulateEvent() {
        return playerArmorStandManipulateEvent;
    }

    public EntityDamageItemEvent getEntityDamageItemEvent() {
        return entityDamageItemEvent;
    }

    public void setCancelled(boolean cancel){
        switch(eventType)
        {
            case BLOCK_BREAK_EVENT:
                getBlockBreakEvent().setCancelled(cancel);
                break;
            case BLOCK_PLACE_EVENT:
                getBlockPlaceEvent().setCancelled(cancel);
                break;
            case ENTITY_CHANGE_BLOCK_EVENT:
                getEntityChangeBlockEvent().setCancelled(cancel);
                break;
            case PLAYER_INTERACT_EVENT:
                getPlayerInteractEvent().setCancelled(cancel);
                break;
            case PLAYER_INTERACT_ENTITY_EVENT:
                getPlayerInteractEntityEvent().setCancelled(cancel);
                break;
            case HANGING_BREAK_BY_ENTITY_EVENT:
                getHangingBreakByEntityEvent().setCancelled(cancel);
                break;
            case ENTITY_DAMAGE_BY_ENTITY_EVENT:
                getEntityDamageByEntityEvent().setCancelled(cancel);
                break;
            case PLAYER_ARMOR_STAND_MANIPULATE_EVENT:
                getPlayerArmorStandManipulateEvent().setCancelled(cancel);
                break;
            case ENTITY_DAMAGE_ITEM_EVENT:
                getEntityDamageItemEvent().setCancelled(cancel);
                break;
        }
    }

    public HandlerList getHandlers(){
        return handlers;
    }

    public static HandlerList getHandlerList(){
        return handlers;
    }
}
