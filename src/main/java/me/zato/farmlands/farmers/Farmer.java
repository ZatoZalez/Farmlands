package me.zato.farmlands.farmers;

import me.zato.farmlands.communication.Messenger;
import me.zato.farmlands.farms.Farm;
import me.zato.farmlands.farms.Farms;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

public class Farmer {
    private String id;
    private UUID playerid;
    private List<String> farms = new ArrayList<>();
    private String defaultfarm;
    private String selectedfarm;
    private double balance = 0;
    private int farmland = 0;


    public Farmer(Player player){
        this.id = UUID.randomUUID().toString();
        this.playerid = player.getUniqueId();
    }

    public Farmer(String id, String playerid){
        this.id = id;
        this.playerid = UUID.fromString(playerid);
    }


    //farmer data
    public boolean isOnline(){ return (Bukkit.getPlayer(playerid) != null); }

    public Player getPlayer(){ return Bukkit.getPlayer(playerid); }

    public OfflinePlayer getOfflinePlayer(){ return Bukkit.getOfflinePlayer(playerid); }

    public UUID getPlayerId(){ return playerid; }

    public String getId(){ return id; }


    //economy
    public void setBalance(double value){ balance = value; }

    public double getBalance(){ return balance; }

    public void depositBalance(double value){ balance += value; }

    public void withdrawBalance(double value){ balance -= value; }

    public boolean hasBalance(double value){ return (balance >= value); }

    public int getFarmland(){ return farmland; }

    public void addFarmland(int amount){ farmland += amount; }

    public void removeFarmland(int amount){ farmland -= amount; }

    public void setFarmland(int amount){ farmland = amount; }


    //farms
    public void addFarm(Farm farm) { farms.add(farm.getId()); }

    public void addFarm(String farmid) { farms.add(farmid); }

    public void removeFarm(Farm farm) { farms.remove(farm.getId()); }

    public int getFarmCount() { return getFarms().size(); }

    public List<Farm> getFarms() {
        List<Farm> farmlist = new ArrayList<>();
        for(String farm : farms)
            farmlist.add(Farms.get(farm));
        return farmlist;
    }

    public Farm getDefaultFarm() { if(defaultfarm == null) return null; return Farms.get(defaultfarm); }

    public void setDefaultFarm(Farm farm) { if(farm != null) defaultfarm = farm.getId(); else defaultfarm = null; }

    public void setDefaultFarm(String farmid) { defaultfarm = farmid; }

    public Farm getSelectedFarm() { if(selectedfarm == null) return null; return Farms.get(selectedfarm); }

    public void setSelectedFarm(Farm farm) { if(farm != null) selectedfarm = farm.getId(); else selectedfarm = null; }

    public Farm getCurrentFarm() { if(isOnline()) return Farms.getFarm(getPlayer().getWorld()); return null; }

    public Farm getDefaultOrCurrentFarm() {
        if(isOnFarmersFarm())
            return getCurrentFarm();
        return getDefaultFarm();

    }

    public Farm getSelectedOrCurrentFarm() {
        Farm farm = getSelectedFarm();
        if(isOnFarmersFarm() && farm == null)
            return getCurrentFarm();
        return farm;
    }

    public boolean isOnAFarm() { return (getCurrentFarm() != null); }

    public boolean hasFarm() { return (getFarms().size() > 0 && defaultfarm != null); }

    public boolean isFarmersFarm(Farm farm) { if(farm == null) return false; return (farms.contains(farm.getId())); }

    public boolean isOnFarmersFarm() {
        Farm farm = getCurrentFarm();
        if(farm == null || !isOnline())
            return false;
        if(isFarmersFarm(farm))
            return true;
        return false;
    }

    public boolean isInsideFarmBorder(){
        Farm farm = getCurrentFarm();
        if(farm == null || !isOnline())
            return false;
        if(getPlayer().getLocation().distance(farm.getWorld().getSpawnLocation()) <= farm.getBordersize())
            return true;
        return false;
    }


    //inventory
    public boolean hasItem(Material item){
        return hasItem(item, 1);
    }

    public boolean hasItem(Material item, int amount){
        if(!isOnline() || item == null || amount < 1)
            return false;

        int i = 0;
        for(ItemStack itemStack : getPlayer().getInventory().getContents())
            if(itemStack != null)
                if(itemStack.getType().equals(item)) {
                    i += itemStack.getAmount();
                    if (i >= amount)
                        return true;
                }
        return false;
    }

    public void giveItem(Material item){
        giveItem(item, 1);
    }

    public void giveItem(Material item, int amount){
        if(item == null)
            return;
        giveItem(new ItemStack(item, amount));
    }

    public void giveItem(ItemStack itemStack){
        if(!isOnline() || itemStack == null)
            return;
        getPlayer().getInventory().addItem(itemStack);
    }

    public void takeItem(Material item){
        takeItem(item, 1);
    }

    public void takeItem(Material item, int amount){
        if(!isOnline() || item == null || amount < 1)
            return;
        if(!hasItem(item, amount))
            return;

        for(int i = 0; i < getPlayer().getInventory().getSize(); i++) {
            ItemStack itm = getPlayer().getInventory().getItem(i);
            if(itm != null && itm.getType().equals(item)){
                int amt = itm.getAmount() - amount;
                itm.setAmount(amt);
                getPlayer().getInventory().setItem(i, amt > 0 ? itm : null);
                break;
            }
        }
    }


    //other
    public void sendMessage(String message){
        if(isOnline())
            Messenger.write(getPlayer(), message);
    }

    public void sendWarning(String message){
        if(isOnline())
            Messenger.write(getPlayer(), "§6" + message);
    }

    public void sendError(String message){
        if(isOnline())
            Messenger.write(getPlayer(), "§l§4ERROR §r§c" + message);
    }


    //storage
    public String getInline() {
        LinkedHashMap<String, String> inlineMap = new LinkedHashMap<>();
        inlineMap.put("playerid", playerid.toString());
        if(defaultfarm != null)
            inlineMap.put("defaultfarm", defaultfarm);
        inlineMap.put("balance", balance + "");
        inlineMap.put("farmland", farmland + "");
        if (farms.size() > 0)
            inlineMap.put("farms", String.join(",", farms));

        String inline = "";
        for (String key : inlineMap.keySet()) {
            if(!inline.equals(""))
                inline += "\n";
            inline += key + ":" + inlineMap.get(key);
        }
        return inline;
    }
}
