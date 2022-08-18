package me.zato.farmlands;

import me.zato.farmlands.commands.*;
import me.zato.farmlands.config.ConfigUtility;
import me.zato.farmlands.database.DatabaseFarmUtility;
import me.zato.farmlands.database.DatabaseFarmerUtility;
import me.zato.farmlands.economy.shop.ItemUtility;
import me.zato.farmlands.listeners.custom.*;
import me.zato.farmlands.listeners.standard.*;
import me.zato.farmlands.utils.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Farmlands extends JavaPlugin {

    private static Farmlands plugin;
    @Override
    public void onEnable() {
        // Plugin startup logic
        initializePlugin();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        ConfigUtility.save();
        DatabaseFarmerUtility.save();
        DatabaseFarmUtility.save();
    }

    private void initializePlugin() {
        plugin = this;
        Bukkit.getConsoleSender().sendMessage(getDescription().getFullName() + " by " +
                ChatColor.RED + getDescription().getAuthors().toString().replace("[", "").replace("]", ""));
        FileManager.routineCheck();

        //listeners
        new PlayerJoinListener(this);
        new PlayerDamageListener(this);
        new PlayerChatListener(this);
        new PlayerInventoryListener(this);

        new PlayerInteractListener(this);
        new PlayerMoveListener(this);
        new PlayerDeathListener(this);
        new PlayerTeleportListener(this);

        new BlockBreakListener(this);

        //custom listeners
        new FarmerInteractListener(this);
        new FarmerMoveListener(this);
        new FarmerDeathListener(this);
        new FarmerBuyListener(this);
        new FarmerSellListener(this);
        new FarmerTeleportListener(this);

        //commands
        getCommand("farms").setExecutor(new FarmsCommand());
        getCommand("farm").setExecutor(new FarmCommand());
        getCommand("farm").setTabCompleter(new FarmTab());
        getCommand("shop").setExecutor(new ShopCommand());
        getCommand("balance").setExecutor(new BalanceCommand());
        getCommand("give").setExecutor(new GiveCommand());
        getCommand("land").setExecutor(new LandCommand());

        getCommand("debug").setExecutor(new DebugCommand());

        //storage
        ConfigUtility.read();
        ItemUtility.read();
        DatabaseFarmerUtility.read();
        DatabaseFarmUtility.read();
    }

    public static Farmlands getPlugin(){
        return plugin;
    }
}
