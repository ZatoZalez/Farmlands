package me.zato.farmlands.commands;

import me.zato.farmlands.communication.Messages;
import me.zato.farmlands.farmers.Farmer;
import me.zato.farmlands.farmers.Farmers;
import me.zato.farmlands.farms.Farm;
import me.zato.farmlands.farms.Farms;
import me.zato.farmlands.gui.FarmRulesGUI;
import me.zato.farmlands.gui.FarmShopGUI;
import me.zato.farmlands.gui.FarmMenuGUI;
import me.zato.farmlands.gui.ItemShopGUI;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

public class FarmCommand implements CommandExecutor {
    @Override
    public boolean onCommand (CommandSender sender, Command command, String string, String []args) {
        if (!(sender instanceof Player))
            return false;

        Player p = ((Player) sender).getPlayer();
        if (!Farmers.isFarmer(p))
            return true;

        Farmer farmer = Farmers.getFarmer(p);
        if (args.length < 1){
            if (!farmer.hasFarm()) {
                FarmShopGUI farmShopGUI = new FarmShopGUI(farmer);
                farmShopGUI.openInventory(p);
                return true;
            }
        }

        if(args.length == 0) {
            menu(farmer);
            return true;
        }

        switch(args[0]){
            case "home":
                home(farmer);
                return true;
            case "sethome":
                sethome(farmer);
                return true;
            case "warp":
                warp(farmer, args);
                return true;
            case "setwarp":
                setwarp(farmer);
                return true;
            case "rename":
                rename(farmer, args);
                return true;
            case "menu":
                menu(farmer);
                return true;
            case "shop":
                shop(farmer);
                return true;
            case "rules":
                rules(farmer);
                return true;
            case "delete":
                delete(farmer);
                return true;
            case "help":
                help(farmer);
                return true;
        }

        help(farmer);
        return true;
    }

    private void home(Farmer farmer){
        if(!farmer.hasFarm()){
            farmer.sendWarning(Messages.player_has_no_farm);
            return;
        }

        farmer.sendMessage(Messages.player_teleport_farm);
        farmer.getPlayer().teleport(farmer.getDefaultOrCurrentFarm().getHome());
    }

    private void sethome(Farmer farmer){
        if(!farmer.isOnFarmersFarm() || !farmer.isInsideFarmBorder()){
            farmer.sendWarning("You have to be on your own farm to perform this action.");
            return;
        }

        World world = farmer.getCurrentFarm().getWorld();
        if(world == null)
            return;
        world.setSpawnLocation(farmer.getPlayer().getLocation());
        farmer.getCurrentFarm().setHome(farmer.getPlayer().getLocation());
        farmer.sendMessage("New §6farm§f home has been set!");
    }

    private void warp(Farmer farmer, String []args){
        if(args.length < 2){
            farmer.sendWarning("Incorrect usage of command. Use §c/farm warp <farmname>§f.");
            return;
        }

        List<String> list = new LinkedList<String>(Arrays.asList(args));
        list.remove(0);
        String farmName = String.join(" ", list);
        farmName = farmName.replace("_", " ");

        Farm farm = Farms.getFarm(farmName);
        if(farm == null || !farm.isPublic()){
            farmer.sendWarning("§c" + farmName + "§f is not a public farm and cannot be warped to.");
            return;
        }

        farmer.sendMessage("Teleporting you to §6" + farmName + "§f...");
        farmer.getPlayer().teleport(farm.getWarp());
    }

    private void setwarp(Farmer farmer){
        if(!farmer.isOnFarmersFarm() || !farmer.isInsideFarmBorder()){
            farmer.sendWarning("You have to be on your own farm to perform this action.");
            return;
        }

        World world = farmer.getCurrentFarm().getWorld();
        if(world == null)
            return;

        farmer.getCurrentFarm().setWarp(farmer.getPlayer().getLocation());
        farmer.sendMessage("New farm §6warp§f has been set!");
    }

    private void rename(Farmer farmer, String []args){
        if(!farmer.hasFarm()){
            farmer.sendWarning(Messages.player_has_no_farm);
            return;
        }

        Farm farm = farmer.getDefaultOrCurrentFarm();
        if(args.length < 2){
            farmer.setSelectedFarm(farm);
            ChatMessage.addAwaitingMessage(farmer.getPlayer(), ChatMessage.TYPE.RENAME);
            farmer.sendMessage(Messages.player_rename_through_chat);
            return;
        }

        List<String> list = new LinkedList<String>(Arrays.asList(args));
        list.remove(0);
        if(list.size() > 2){
            farmer.sendWarning(Messages.warning_rename_more_than_2_words);
            return;
        }

        String farmName = String.join(" ", list);
        if(farmName.length() < 5){
            farmer.sendWarning(Messages.warning_rename_less_than_5_char);
            return;
        }

        if(farmName.length() > 20){
            farmer.sendWarning(Messages.warning_rename_more_than_20_char);
            return;
        }

        if(Pattern.matches("\\p{Punct}", farmName)){
            farmer.sendWarning("You can't use any §cpunctuation§f in your farm name.");
            return;
        }

        Farm thirdFarm = Farms.getFarm(farmName);
        if(thirdFarm != null){
            farmer.sendWarning("Farm name already exists. Please try again.");
            ChatMessage.addAwaitingMessage(farmer.getPlayer(), ChatMessage.TYPE.RENAME);
            return;
        }

        farm.setName(farmName);
        farmer.sendMessage("You successfully changed your farm name to §6" + farm.getName() + "§f!");
    }

    public static void rename(Player player, String text){
        Farmer farmer = Farmers.getFarmer(player);
        if(farmer == null)
            return;

        if(text.equalsIgnoreCase("cancel")){
            farmer.setSelectedFarm(null);
            farmer.sendMessage(Messages.player_rename_cancel);
            return;
        }

        if(text.length() < 5){
            farmer.sendWarning(Messages.warning_rename_less_than_5_char);
            return;
        }

        if(!farmer.hasFarm()){
            farmer.sendWarning(Messages.player_has_no_farm);
            return;
        }

        List<String> list = new ArrayList<String>(Arrays.asList(text.split(" ")));
        if(list.size() > 2){
            farmer.sendWarning(Messages.warning_rename_more_than_2_words);
            return;
        }

        if(text.length() > 20){
            farmer.sendWarning(Messages.warning_rename_more_than_20_char);
            return;
        }

        if(Pattern.matches("\\p{Punct}", text)){
            farmer.sendWarning("You can't use any §cpunctuation§f in your farm name. ");
            return;
        }

        Farm thirdFarm = Farms.getFarm(text);
        if(thirdFarm != null){
            farmer.sendWarning("Farm name already exists.");
            return;
        }

        String farmName = text;

        Farm farm = farmer.getSelectedOrCurrentFarm();
        farm.setName(farmName);
        farmer.sendMessage("You successfully changed your farm name to §6" + farm.getName() + "§f!");
    }

    private void shop(Farmer farmer){
        farmer.setSelectedFarm(farmer.getDefaultOrCurrentFarm());
        ItemShopGUI itemShopGUI = new ItemShopGUI(farmer);
        itemShopGUI.openInventory(farmer.getPlayer());
    }

    private void rules(Farmer farmer){
        if(!farmer.hasFarm()){
            farmer.sendWarning(Messages.player_has_no_farm);
            return;
        }

        farmer.setSelectedFarm(farmer.getDefaultOrCurrentFarm());
        FarmRulesGUI farmRulesGUI = new FarmRulesGUI(farmer);
        farmRulesGUI.openInventory(farmer.getPlayer());
    }

    private void delete(Farmer farmer){
        if(!farmer.hasFarm()){
            farmer.sendWarning(Messages.player_has_no_farm);
            return;
        }

        Farm farm = farmer.getDefaultOrCurrentFarm();
        if(farm == null){
            farmer.sendError("Could not delete farm due to an error...");
            return;
        }
        farmer.sendMessage("Deleting " + farm.getName() + "...");
        farm.permanentlyDelete();
        farmer.sendMessage("Deleted " + farm.getName() + ".");
    }

    private void menu(Farmer farmer){
        farmer.setSelectedFarm(farmer.getDefaultOrCurrentFarm());
        FarmMenuGUI farmMenuGUI = new FarmMenuGUI(farmer);
        farmMenuGUI.openInventory(farmer.getPlayer());
    }

    private void help(Farmer farmer){
        farmer.sendMessage("You can use the following farm commands:\n" +
                "   - §6/farm§f or §6/farm menu§f\n" +
                "   - §6/farm home§f or §6/farm sethome§f\n" +
                "   - §6/farm warp <player>§f or §6/farm setwarp§f\n" +
                "   - §6/farm rename <name>§f\n" +
                "   - §6/farm shop§f or §6/shop§f\n" +
                "   - §6/farm rules§f\n" +
                "   - §6/farm delete§f\n" +
                "   - §6/farm help§f\n" +
                "");
    }
}
