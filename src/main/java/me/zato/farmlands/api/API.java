package me.zato.farmlands.api;

import me.zato.farmlands.farmers.Farmer;
import me.zato.farmlands.farmers.Farmers;
import org.bukkit.entity.Player;

public class API {
    public Farmer getFarmer(Player player){
        return Farmers.getFarmer(player);
    }

    public void sendMessage(Player player, String message){
        if(Farmers.isFarmer(player))
            sendMessage(getFarmer(player), message);
    }

    public void sendMessage(Farmer farmer, String message){
        farmer.sendMessage(message);
    }
}
