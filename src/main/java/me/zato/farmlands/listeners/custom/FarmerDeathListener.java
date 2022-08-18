package me.zato.farmlands.listeners.custom;

import me.zato.farmlands.Farmlands;
import me.zato.farmlands.events.FarmerDeathEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class FarmerDeathListener implements Listener {
    public FarmerDeathListener(Farmlands plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onFarmerDeath(FarmerDeathEvent e){
        e.setDeathMessage(null);
    }
}
