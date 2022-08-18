package me.zato.farmlands.farmers;

import org.bukkit.entity.Player;

import java.util.LinkedHashMap;

public class Farmers {
    private static LinkedHashMap<String, Farmer> farmers = new LinkedHashMap<>();

    public static LinkedHashMap<String, Farmer> getList(){
        return farmers;
    }

    public static Farmer add(Farmer farmer){
        for (Farmer f : farmers.values()){
            if(f.getId().equalsIgnoreCase(farmer.getId()))
                return f;
        }
        farmers.put(farmer.getId(), farmer);
        return farmer;
    }

    public static Farmer add(boolean override, Farmer farmer){
        for (int i = 0; i < farmers.size(); i++)
            if(farmers.get(i).getId().equalsIgnoreCase(farmer.getId())){
                if(!override)
                    farmers.remove(farmers.get(i));
                else
                    return farmers.get(i);
            }
        farmers.put(farmer.getId(), farmer);
        return farmer;
    }

    public static Farmer get(String farmerid){
        return farmers.get(farmerid);
    }

    public static void remove(String farmerid){
        farmers.remove(farmerid);
    }

    public static Farmer getFarmer(Player player){
        if(player == null)
            return null;
        if(farmers != null && farmers.size() > 0)
            for(Farmer farmer : farmers.values())
                if(farmer != null)
                    if(farmer.getPlayerId().equals(player.getUniqueId()))
                        return farmer;
        return null;
    }

    public static Farmer getFarmer(String playerid){
        if(playerid == null)
            return null;
        if(farmers != null && farmers.size() > 0)
            for(Farmer farmer : farmers.values())
                if(farmer != null)
                    if(farmer.getPlayerId().equals(playerid))
                        return farmer;
        return null;
    }

    public static boolean isFarmer(Player player){
        if(player == null)
            return false;
        if(farmers != null && farmers.size() > 0)
            for(Farmer farmer : farmers.values())
                if(farmer != null)
                    if(farmer.getPlayerId().equals(player.getUniqueId()))
                        return true;
        return false;
    }
}
