package me.zato.farmlands.commands;

import me.zato.farmlands.economy.Utils;
import me.zato.farmlands.farmers.Farmer;
import me.zato.farmlands.farmers.Farmers;
import me.zato.farmlands.utils.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GiveCommand implements CommandExecutor {
    @Override
    public boolean onCommand (CommandSender sender, Command command, String string, String []args){
        if (!(sender instanceof Player))
            return false;

        Player p = ((Player) sender).getPlayer();
        if(!Farmers.isFarmer(p))
            return true;

        Farmer farmer = Farmers.getFarmer(p);
        if (args.length != 2){
            farmer.sendWarning("Incorrect usage of command. Use §c/give <player> <amount>§f.");
            return true;
        }

        Player player = Bukkit.getPlayer(args[0]);
        if(player == null || !Farmers.isFarmer(player)){
            farmer.sendWarning("Player §c" + args[0] + "§f does not exist or is offline.");
            return true;
        }

        int amount = FileManager.getIntFromString(args[1]);
        if(amount < 1){
            farmer.sendWarning("Invalid amount of §c" + args[1] + "§f.");
            return true;
        }

        if(farmer.getPlayer().equals(player)){
            farmer.sendWarning("You can't give money to yourself.");
            return true;
        }

        if(!farmer.hasBalance(amount)){
            farmer.sendWarning("You do not have enough balance to give §c$" + Utils.convertToVisualValue(amount) + "§f.");
            return true;
        }

        Farmer targetFarmer = Farmers.getFarmer(player);
        farmer.withdrawBalance(amount);
        targetFarmer.depositBalance(amount);
        farmer.sendMessage("You successfully gave §6$" + Utils.convertToVisualValue(amount) + "§f to §6" + targetFarmer.getPlayer().getDisplayName() + "§f!");
        targetFarmer.sendMessage("You received §6$" + Utils.convertToVisualValue(amount) + "§f from §6" + farmer.getPlayer().getDisplayName()+ "§f!");
        return true;
    }
}