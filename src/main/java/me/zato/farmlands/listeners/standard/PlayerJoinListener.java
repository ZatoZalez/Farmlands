package me.zato.farmlands.listeners.standard;

import me.zato.farmlands.Farmlands;
import me.zato.farmlands.communication.Messages;
import me.zato.farmlands.farmers.Farmer;
import me.zato.farmlands.farmers.Farmers;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    public PlayerJoinListener(Farmlands plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if(p == null)
            return;

        if(!Farmers.isFarmer(p)){
            Farmer farmer = new Farmer(p);
            farmer.sendMessage(Messages.new_player_join);
            Farmers.add(farmer);
            return;
        }

        Farmer farmer = Farmers.getFarmer(p);
        if(farmer.hasFarm())
            farmer.sendMessage(Messages.player_join);
        else
            farmer.sendMessage(Messages.new_player_join);
        return;
    }
}
