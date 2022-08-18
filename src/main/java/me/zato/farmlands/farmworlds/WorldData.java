package me.zato.farmlands.farmworlds;

import me.zato.farmlands.farms.Farm;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.List;

public class WorldData {
    public static List<Block> getNearbyBlocks(Location location, int radius) {
        List<Block> blocks = new ArrayList<Block>();
        for(int x = location.getBlockX() - radius; x <= location.getBlockX() + radius; x++) {
            for(int y = location.getBlockY() - radius; y <= location.getBlockY() + radius; y++) {
                for(int z = location.getBlockZ() - radius; z <= location.getBlockZ() + radius; z++) {
                    blocks.add(location.getWorld().getBlockAt(x, y, z));
                }
            }
        }
        return blocks;
    }

    public static void constructBarrier(Farm farm){
        Location center = farm.getCenterPoint().getBlock().getLocation();
        int radius = farm.getBordersize();

        Material material = Material.BARRIER;
        Location loc = center;
        loc.set(loc.getBlockX() + radius, loc.getBlockY(), loc.getBlockZ() + radius);
        for(int i = 1; i <= (radius * 2); i++){
            loc.set(loc.getBlockX() - 1, loc.getBlockY(), loc.getBlockZ());
            extendBarrier(loc.getBlock(), material);
        }

        for(int i = 1; i <= (radius * 2); i++){
            loc.set(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ() - 1);
            extendBarrier(loc.getBlock(), material);
        }

        for(int i = 1; i <= (radius * 2); i++){
            loc.set(loc.getBlockX() + 1, loc.getBlockY(), loc.getBlockZ());
            extendBarrier(loc.getBlock(), material);
        }

        for(int i = 1; i <= (radius * 2); i++){
            loc.set(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ() + 1);
            extendBarrier(loc.getBlock(), material);
        }
    }

    private static void extendBarrier(Block block, Material material){
        Location loc = block.getLocation();
        loc.set(loc.getBlockX(), -64, loc.getBlockZ());
        for(int i = -64; i <= 320; i++){
            loc.set(loc.getBlockX(), i, loc.getBlockZ());
            loc.getBlock().setType(material);
        }
    }
}
