package me.zato.farmlands.farms;

import me.zato.farmlands.communication.Logger;
import me.zato.farmlands.communication.Messages;
import me.zato.farmlands.farmers.Farmer;
import me.zato.farmlands.farmers.Farmers;
import me.zato.farmlands.farmworlds.WorldData;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;

import java.util.LinkedHashMap;
import java.util.UUID;

import static me.zato.farmlands.farmworlds.WorldFarms.createFarmWorld;

public class Farms {
    private static LinkedHashMap<String, Farm> farms = new LinkedHashMap<>();

    public static LinkedHashMap<String, Farm> getList(){
        return farms;
    }

    public static Farm add(Farm farm){
        for (Farm f : farms.values()){
            if(f.getId().equalsIgnoreCase(farm.getId()))
                return f;
        }
        farms.put(farm.getId(), farm);
        return farm;
    }

    public static Farm add(boolean override, Farm farm){
        for (int i = 0; i < farms.size(); i++)
            if(farms.get(i).getId().equalsIgnoreCase(farm.getId())){
                if(!override)
                    farms.remove(farms.get(i));
                else
                    return farms.get(i);
            }
        farms.put(farm.getId(), farm);
        return farm;
    }

    public static Farm get(String farmid){
        return farms.get(farmid);
    }

    public static void remove(String farmid){
        farms.remove(farmid);
    }

    public static Farm getFarm(World world){
        if(world == null)
            return null;
        if(farms != null && farms.size() > 0)
            for(Farm farm : farms.values())
                if(farm != null)
                    if(farm.getWorld().equals(world))
                        return farm;
        return null;
    }

    public static Farm getFarm(String farmname){
        if(farmname == null)
            return null;
        if(farms != null && farms.size() > 0)
            for(Farm farm : farms.values())
                if(farm != null)
                    if(farm.getName().equalsIgnoreCase(farmname))
                        return farm;
        return null;
    }

    public static Farm build(Farmer farmer){
        farmer.sendMessage(Messages.player_bought_farm);
        farmer.sendMessage(Messages.player_generating_farm);

        Object[] world = createFarmWorld(farmer.getPlayer());
        if(world == null || world.length != 2)
        {
            farmer.sendMessage(Messages.error_generating_farm);
            return null;
        }

        Farm farm = new Farm((UUID) world[0], (String) world[1], Farmers.getFarmer(farmer.getPlayer()));

        farm.setValue(970); //each farmland is 10 in value.

        //check for farmname
        int i = farmer.getFarms().size() + 1;
        if(Farms.getFarm(farmer.getPlayer().getName() + "'s Farm") != null) {
            while (true) {
                String name = farmer.getPlayer().getName() + "'s Farm" + i;
                if (Farms.getFarm(name) == null) {
                    farm.setName(name);
                    break;
                }
                i++;
            }
        }
        Logger.write("Generated farm " + farm.getId() + " [" + farm.getName() + "]");

        Farms.add(farm);
        farmer.addFarm(farm);

        farmer.setDefaultFarm(farm);
        farmer.sendMessage(Messages.player_teleport_farm);
        farmer.getPlayer().teleport(farm.getWorld().getSpawnLocation());

        //set world border
        farm.setBordersize(50);
        farm.setCenterPoint(farm.getWorld().getSpawnLocation());
        WorldData.constructBarrier(farm);

        //set sign with farmname
        for(Block block : WorldData.getNearbyBlocks(farm.getWorld().getSpawnLocation(), 5)) {
            BlockState state = block.getState();
            if (state instanceof Sign) {
                Sign sign = (Sign) state;
                sign.setLine(1, farm.getFarmer().getPlayer().getName() + "'s");
                sign.setLine(2, "Farm");
                sign.update();
                break;
            }
        }
        return farm;
    }
}
