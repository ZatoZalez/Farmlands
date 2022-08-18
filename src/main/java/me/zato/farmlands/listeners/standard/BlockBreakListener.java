package me.zato.farmlands.listeners.standard;

import me.zato.farmlands.Farmlands;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.scheduler.BukkitScheduler;


public class BlockBreakListener implements Listener {
    public BlockBreakListener(Farmlands plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onBlockBreakByPlayerOrPhysics(BlockBreakEvent e) {
        Block block = e.getBlock();
        refillSpace(block);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onBlockBreakByWater(BlockFromToEvent e) {
        Block block = e.getToBlock();
        refillSpace(block);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onBlockBreakByPiston(BlockPistonExtendEvent e) {
        if(e.getBlocks().size() < 1)
            return;

        for(Block block : e.getBlocks())
        {
            refillSpace(block);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onRedstoneBreakByExplosion(EntityExplodeEvent e) {
        if(e.blockList().size() < 1)
            return;

        for(Block block : e.blockList())
        {
            refillSpace(block);
        }
    }

    public void refillSpace(Block block){
        Material material = block.getType();
        Location location  = block.getLocation();
        BlockData blockData = block.getBlockData();
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.scheduleSyncDelayedTask(Farmlands.getPlugin(), new Runnable() {
            @Override
            public void run() {
                location.getBlock().setType(material);
                location.getBlock().setBlockData(blockData);
            }
        }, 40);
        return;
    }
}
