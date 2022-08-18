package me.zato.farmlands.commands;

import me.zato.farmlands.communication.Messages;
import me.zato.farmlands.farmers.Farmer;
import me.zato.farmlands.farmers.Farmers;
import me.zato.farmlands.gui.FarmsGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FarmsCommand implements CommandExecutor {
    @Override
    public boolean onCommand (CommandSender sender, Command command, String string, String []args){
        if (!(sender instanceof Player))
            return false;

        Player p = ((Player) sender).getPlayer();
        if(!Farmers.isFarmer(p))
            return true;

        Farmer farmer = Farmers.getFarmer(p);
        if(!farmer.hasFarm()){
            farmer.sendWarning(Messages.player_has_no_farm);
            return true;
        }

        FarmsGUI farmsGUI = new FarmsGUI(farmer);
        farmsGUI.openInventory(farmer.getPlayer());
        return true;
    }
}