package me.zato.farmlands.listeners.custom;

import me.zato.farmlands.Farmlands;
import me.zato.farmlands.events.FarmerTeleportEvent;
import me.zato.farmlands.farmers.Farmer;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class FarmerTeleportListener  implements Listener {
    public FarmerTeleportListener(Farmlands plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }


    //@EventHandler
    //public void onFarmerTeleport(FarmerTeleportEvent e){
    //}
}
