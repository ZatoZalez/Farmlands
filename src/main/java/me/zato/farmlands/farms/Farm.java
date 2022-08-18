package me.zato.farmlands.farms;

import me.zato.farmlands.communication.Logger;
import me.zato.farmlands.communication.Messages;
import me.zato.farmlands.communication.Messenger;
import me.zato.farmlands.config.ConfigValue;
import me.zato.farmlands.farmers.Farmer;
import me.zato.farmlands.farmers.Farmers;
import me.zato.farmlands.farmworlds.WorldFarms;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

public class Farm {
    private String id;
    private UUID worldid;
    private String worldname;
    private String farmerid;
    private String name;
    private double value = 0;
    private int bordersize;
    private Location home;
    private Location warp;
    private Location centerpoint;
    private boolean ispublic = false;


    public Farm(UUID worldid, String worldname, Farmer farmer){
        this.id = UUID.randomUUID().toString();
        this.worldid = worldid;
        this.worldname = worldname;
        this.farmerid = farmer.getId();
        this.name = farmer.getPlayer().getName() + "'s Farm";
    }

    public Farm(String id, String worldid, String worldname, String farmerid, String name){
        this.id = id;
        this.worldid = UUID.fromString(worldid);
        this.worldname = worldname;
        this.farmerid = farmerid;
        this.name = name;
    }


    //farm data
    public String getId() { return id; }

    public UUID getWorldId() { return worldid; }

    public World getWorld() {
        World world = Bukkit.getWorld(worldid);
        if(world == null)
            if(WorldFarms.worldExists(worldname))
                return Bukkit.getServer().createWorld(new WorldCreator(worldname));
        return world;
    }

    public String getFarmerId() { return farmerid; }

    public Farmer getFarmer() { return Farmers.get(farmerid); }

    public String getName(){ return name; }

    public void setName(String name) { this.name = name; }

    public int getBordersize(){ return bordersize; }

    public void addBordersize(int amount){ bordersize += amount; }

    public void removeBordersize(int amount){ bordersize -= amount; }

    public void setBordersize(int amount){ bordersize = amount; }

    public void isPublic(boolean ispublic){ this.ispublic = ispublic; }

    public boolean isPublic(){ return ispublic; }

    public void setHome(Location home){ this.home = home; }

    public Location getHome(){ if(this.home == null) return getWorld().getSpawnLocation(); return home; }

    public void setWarp(Location warp){ this.warp = warp; }

    public Location getWarp(){ return warp; }

    public void setCenterPoint(Location centerpoint){ this.centerpoint = centerpoint; }

    public Location getCenterPoint(){ return centerpoint; }

    public List<Farmer> getFarmers(){
        List<Farmer> farmers = new ArrayList<>();
        for(Player player : getWorld().getPlayers())
            if(Farmers.isFarmer(player))
                farmers.add(Farmers.getFarmer(player));
        return farmers;
    }

    public void permanentlyDelete(){
        if(Bukkit.getWorld(ConfigValue.getWorldName()) != null) {
            World world = Bukkit.getWorld(ConfigValue.getWorldName());
            for (Farmer farmer : getFarmers()) {
                farmer.sendMessage("Teleporting to spawn...");
                farmer.getPlayer().teleport(world.getSpawnLocation());
            }
        }
        getFarmer().removeFarm(this);
        Farms.remove(this.getId());


        Logger.write("Deleting farm for " + getFarmer().getPlayer().getName() + "...");

        //unload world
        WorldFarms.unloadWorld(this);

        //delete world
        WorldFarms.deleteWorld(this);
        Logger.write("Deleted farm " + getId() + " [" + getName() + "]");
    }


    //economy
    public double getValue(){ return value; }

    public void addValue(double amount){ value += amount; }

    public void removeValue(double amount){ value -= amount; }

    public void setValue(double amount){ value = amount; }


    //storage
    public String getInline() {
        LinkedHashMap<String, String> inlineMap = new LinkedHashMap<>();
        inlineMap.put("worldid", worldid.toString());
        inlineMap.put("worldname", worldname);
        inlineMap.put("farmerid", farmerid);
        inlineMap.put("name", name);
        inlineMap.put("value", value + "");
        inlineMap.put("bordersize", bordersize + "");
        inlineMap.put("ispublic", (ispublic + "").toLowerCase());
        inlineMap.put("centerpoint", centerpoint.getBlockX() + "," + centerpoint.getBlockY() + "," + centerpoint.getBlockZ());
        if(home != null)
            inlineMap.put("home", home.getBlockX() + "," + home.getBlockY() + "," + home.getBlockZ());
        if(warp != null)
            inlineMap.put("warp", warp.getBlockX() + "," + warp.getBlockY() + "," + warp.getBlockZ());

        String inline = "";
        for (String key : inlineMap.keySet()) {
            if(!inline.equals(""))
                inline += "\n";
            inline += key + ":" + inlineMap.get(key);
        }
        return inline;
    }
}
