package me.zato.farmlands.listeners.standard;

import io.papermc.paper.event.entity.EntityDamageItemEvent;
import me.zato.farmlands.Farmlands;
import me.zato.farmlands.events.FarmerInteractEvent;
import me.zato.farmlands.farmers.Farmers;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {
    public PlayerInteractListener(Farmlands plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerBreakBlock(BlockBreakEvent e) {
        if(e.getPlayer() != null)
            if(Farmers.isFarmer(e.getPlayer()))
                Bukkit.getServer().getPluginManager().callEvent(new FarmerInteractEvent(Farmers.getFarmer(e.getPlayer()), e));
    }

    @EventHandler
    public void onPlayerPlaceBlock(BlockPlaceEvent e) {
        if(e.getPlayer() != null)
            if(Farmers.isFarmer(e.getPlayer()))
                Bukkit.getServer().getPluginManager().callEvent(new FarmerInteractEvent(Farmers.getFarmer(e.getPlayer()), e));
    }

    @EventHandler
    public void onPlayerChangeBlock(EntityChangeBlockEvent e) {
        if(e.getEntity() != null)
            if(e.getEntity() instanceof Player)
                if(Farmers.isFarmer((Player)e.getEntity()))
                    Bukkit.getServer().getPluginManager().callEvent(new FarmerInteractEvent(Farmers.getFarmer((Player)e.getEntity()), e));
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if(e.getPlayer() != null)
                if(Farmers.isFarmer(e.getPlayer()))
                    Bukkit.getServer().getPluginManager().callEvent(new FarmerInteractEvent(Farmers.getFarmer(e.getPlayer()), e));
    }

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent e) {
        if(e.getPlayer() != null)
            if(Farmers.isFarmer(e.getPlayer()))
                Bukkit.getServer().getPluginManager().callEvent(new FarmerInteractEvent(Farmers.getFarmer(e.getPlayer()), e));
    }

    @EventHandler
    public void onHangingBreakByEntity(HangingBreakByEntityEvent e) {
        if(e.getEntity() != null && e.getRemover() != null)
            if(e.getRemover() instanceof Player)
                if(Farmers.isFarmer((Player)e.getRemover()))
                    Bukkit.getServer().getPluginManager().callEvent(new FarmerInteractEvent(Farmers.getFarmer((Player)e.getRemover()), e));
    }

    @EventHandler
    public void onPlayerDamageEntity(EntityDamageByEntityEvent e) {
        if(e.getEntity() != null && e.getDamager() != null)
            if(e.getDamager() instanceof Player)
                if(Farmers.isFarmer((Player)e.getDamager()))
                    Bukkit.getServer().getPluginManager().callEvent(new FarmerInteractEvent(Farmers.getFarmer((Player)e.getDamager()), e));
    }

    @EventHandler
    public void onPlayerModifyArmorStand(PlayerArmorStandManipulateEvent e) {
        if(e.getPlayer() != null)
            if(Farmers.isFarmer(e.getPlayer()))
                Bukkit.getServer().getPluginManager().callEvent(new FarmerInteractEvent(Farmers.getFarmer(e.getPlayer()), e));
    }

    @EventHandler
    public void onEntityDamageItem(EntityDamageItemEvent e) {
        if(e.getEntity() != null)
            if(e.getEntity() instanceof Player)
                if(Farmers.isFarmer((Player)e.getEntity()))
                    Bukkit.getServer().getPluginManager().callEvent(new FarmerInteractEvent(Farmers.getFarmer((Player)e.getEntity()), e));
    }
}
