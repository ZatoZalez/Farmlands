package me.zato.farmlands.listeners.custom;

import me.zato.farmlands.Farmlands;
import me.zato.farmlands.events.FarmerMoveEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.util.Vector;

public class FarmerMoveListener  implements Listener {
    public FarmerMoveListener(Farmlands plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }


    @EventHandler
    public void onFarmerMove(FarmerMoveEvent e){
        if(e.getPlayer().getLocation().getY() <= -70)
            if(!e.getFarmer().isOnAFarm()){
                e.getPlayer().setVelocity(new Vector(0, 0, 0));
                e.getPlayer().teleport(e.getPlayer().getWorld().getSpawnLocation());
            }
    }
}
