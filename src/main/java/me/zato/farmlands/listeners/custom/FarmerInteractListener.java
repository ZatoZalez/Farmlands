package me.zato.farmlands.listeners.custom;

import me.zato.farmlands.Farmlands;
import me.zato.farmlands.events.FarmerInteractEvent;
import me.zato.farmlands.farmers.Farmer;
import me.zato.farmlands.farms.Farm;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

public class FarmerInteractListener implements Listener {
    public FarmerInteractListener(Farmlands plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    //In spawn
    @EventHandler
    public void onFarmerInteractSpawn(FarmerInteractEvent e){
        Farmer farmer = e.getFarmer();

        if(farmer.isOnAFarm())
            return;

        //e.setCancelled(true);
    }

    //In another farmers farm
    @EventHandler
    public void onFarmerInteractAnotherFarm(FarmerInteractEvent e){
        Farmer farmer = e.getFarmer();
        if(!farmer.isOnAFarm() || farmer.isOnFarmersFarm())
            return;

        e.setCancelled(true);
    }

    //In farm
    @EventHandler
    public void onFarmerInteractFarm(FarmerInteractEvent e){
        Farmer farmer = e.getFarmer();
        if(!farmer.isOnFarmersFarm())
            return;

        if(!farmer.isInsideFarmBorder()){
            e.setCancelled(true);
        }
    }

    //Breaking barrier
    @EventHandler
    public void onFarmerInteractBarrier(FarmerInteractEvent e){
        if(e.getEventType().equals(FarmerInteractEvent.EventType.BLOCK_BREAK_EVENT))
            if(e.getBlockBreakEvent().getBlock().getType().equals(Material.BARRIER))
                e.setCancelled(true);
    }

    //Creating farmland
    @EventHandler
    public void onPlayerUseHoe(FarmerInteractEvent e) {
        Farmer farmer = e.getFarmer();
        Farm farm = farmer.getCurrentFarm();

        if(!e.getEventType().equals(FarmerInteractEvent.EventType.PLAYER_INTERACT_EVENT))
            return;

        if (!farmer.isFarmersFarm(farm) || !e.getPlayerInteractEvent().getAction().equals(Action.RIGHT_CLICK_BLOCK) || (!e.getPlayerInteractEvent().getClickedBlock().getType().equals(Material.DIRT) && !e.getPlayerInteractEvent().getClickedBlock().getType().equals(Material.GRASS_BLOCK)))
            return;

        if(farmer.getPlayer().getItemInHand().getType().toString().endsWith("HOE"))
            if(farmer.getFarmland() > 0){
                farmer.removeFarmland(1);
                return;
            }
            else{
                e.setCancelled(true);
                farmer.sendMessage("You do not have enough farmland to create soil. Go to the §6/shop§f to buy farmland.");
            }
    }
}
