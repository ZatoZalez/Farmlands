package me.zato.farmlands.listeners.standard;

import me.zato.farmlands.Farmlands;
import me.zato.farmlands.events.FarmerMoveEvent;
import me.zato.farmlands.farmers.Farmers;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {
    public PlayerMoveListener(Farmlands plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        if(e.getPlayer() != null)
            if(Farmers.isFarmer(e.getPlayer()))
                Bukkit.getServer().getPluginManager().callEvent(new FarmerMoveEvent(Farmers.getFarmer(e.getPlayer()), e));
    }
}
