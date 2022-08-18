package me.zato.farmlands.listeners.standard;

import me.zato.farmlands.Farmlands;
import me.zato.farmlands.events.FarmerDeathEvent;
import me.zato.farmlands.farmers.Farmers;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {
    public PlayerDeathListener(Farmlands plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        if(e.getPlayer() != null)
            if(Farmers.isFarmer(e.getPlayer()))
                Bukkit.getServer().getPluginManager().callEvent(new FarmerDeathEvent(Farmers.getFarmer(e.getPlayer()), e));
    }
}
