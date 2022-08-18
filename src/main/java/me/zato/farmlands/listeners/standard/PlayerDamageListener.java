package me.zato.farmlands.listeners.standard;

import me.zato.farmlands.Farmlands;
import me.zato.farmlands.config.ConfigValue;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class PlayerDamageListener implements Listener {
    public PlayerDamageListener(Farmlands plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onEntityHungry(FoodLevelChangeEvent e) {
        if(e.getEntity().getWorld().getName().equals(ConfigValue.getWorldName()))
            e.setCancelled(true);

        if(e.getFoodLevel() < 20)
            e.setFoodLevel(20);
    }
}
