package me.zato.farmlands.commands;

import me.zato.farmlands.farmers.Farmer;
import me.zato.farmlands.farmers.Farmers;
import me.zato.farmlands.gui.FarmShopGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FarmlandsCommand implements CommandExecutor {
    @Override
    public boolean onCommand (CommandSender sender, Command command, String string, String []args){
        if (!(sender instanceof Player))
            return false;

        Player p = ((Player) sender).getPlayer();
        if(!Farmers.isFarmer(p))
            return true;

        Farmer farmer = Farmers.getFarmer(p);
        if(!farmer.hasFarm()){
            FarmShopGUI farmShopGUI = new FarmShopGUI(farmer);
            farmShopGUI.openInventory(p);
            return true;
        }
        return true;
    }
}
