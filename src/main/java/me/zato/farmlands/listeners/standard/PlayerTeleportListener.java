package me.zato.farmlands.listeners.standard;

import me.zato.farmlands.Farmlands;
import me.zato.farmlands.events.FarmerMoveEvent;
import me.zato.farmlands.farmers.Farmers;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class PlayerTeleportListener implements Listener {
    public PlayerTeleportListener(Farmlands plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent e) {
        if(e.getPlayer() != null)
            if(Farmers.isFarmer(e.getPlayer()))
                Bukkit.getServer().getPluginManager().callEvent(new FarmerMoveEvent(Farmers.getFarmer(e.getPlayer()), e));
    }
}
