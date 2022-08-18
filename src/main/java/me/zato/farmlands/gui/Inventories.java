package me.zato.farmlands.gui;

import me.zato.farmlands.economy.shop.ItemData;
import me.zato.farmlands.economy.shop.Items;
import me.zato.farmlands.farmers.Farmer;
import me.zato.farmlands.farms.Farm;
import org.bukkit.ChatColor;
import org.bukkit.GameRule;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

import static me.zato.farmlands.economy.shop.Items.createGUIItem;

public class Inventories {
    public static Inventory initialize(Inventory inventory, Farmer farmer, GUIHolder.TYPE type){
        if(inventory == null)
            return null;
        switch(type){
            case FARMS:
                return initializeFarms(inventory, farmer);
            case FARM_SHOP:
                return initializeFarmShop(inventory, farmer);
            case FARM_MENU:
                return initializeFarmMenu(inventory, farmer);
            case FARM_RULES:
                return initializeFarmRules(inventory, farmer);
            case ITEM_SHOP:
                return initializeItemShop(inventory, farmer);
            case ITEM_BUY:
                return initializeItemBuy(inventory, farmer);
            case ITEM_SELL:
                return initializeItemSell(inventory, farmer);
        }
        return initializeFarmShop(inventory, farmer);
    }

    private static Inventory initializeFarms(Inventory inventory, Farmer farmer){
        inventory.setItem(0, createGUIItem(Material.GREEN_CONCRETE, ChatColor.GREEN + "Buy new farm", "§7==================", "", "Click to browse the farm shop menu"));

        int i = 2;
        for(Farm farm : farmer.getFarms()){
            inventory.setItem(i, createGUIItem(Material.FARMLAND, ChatColor.GREEN + farm.getName(), "§7==================", "", "§6[Right click]§f to teleport to this farm.", "§6[Left click]§f to open this farm's menu."));
            i++;
        }

        inventory.setItem(8, createGUIItem(Material.RED_CONCRETE, ChatColor.RED + "Close"));
        return inventory;
    }

    private static Inventory initializeFarmShop(Inventory inventory, Farmer farmer){
        inventory.setItem(0, createGUIItem(Material.PAPER, ChatColor.WHITE + "How to buy your first farm?", "§7==================", "", "Each player can buy one farm.", "Your first farm is free of charge", "and can be bought by clicking", "the §6WHEAT item in this menu.", "", "Once you bought your farm,", "you will be teleported", "to your farm where you can start", "your life as farmer."));
        inventory.setItem(2, createGUIItem(Material.WHEAT, ChatColor.GREEN + "Buy Standard Farm", "§7==================", "", "Farm costs: §6$0§f", "Start with a small", "yet comfy and fully equipped farm."));
        inventory.setItem(4, createGUIItem(Material.POTATO, ChatColor.RED + "Buy Medium Farm", "§7==================", "", "Unavailable", "Start with fully set up farm", "with cattle and multiple crops."));
        inventory.setItem(6, createGUIItem(Material.MELON_SLICE, ChatColor.RED + "Buy Large Farm", "§7==================", "", "Unavailable", "Start with an advanced farm", "where you barely need to", "farm by yourself."));
        inventory.setItem(8, createGUIItem(Material.RED_CONCRETE, ChatColor.RED + "Close"));
        return inventory;
    }

    private static Inventory initializeFarmMenu(Inventory inventory, Farmer farmer){
        inventory.setItem(0, createGUIItem(Material.BOOK, ChatColor.GOLD + "View Farms"));

        inventory.setItem(8, createGUIItem(Material.RED_CONCRETE, ChatColor.RED + "Close"));
        inventory.setItem(4, createGUIItem(Material.ENDER_PEARL, ChatColor.LIGHT_PURPLE + "Teleport to farm", "§7==================", "", "You can also use §6/farm home§f"));
        inventory.setItem(19, createGUIItem(Material.EMERALD, ChatColor.GREEN + "Farm shop", "§7==================", "", "You can also use §6/shop§f", "this should open a shop GUI soon."));
        inventory.setItem(21, createGUIItem(Material.WRITABLE_BOOK, ChatColor.BLUE + "Farm rules", "§7==================", "", "Edit §6gamerules§f on your farm."));
        inventory.setItem(23, createGUIItem(Material.NAME_TAG, ChatColor.GOLD + "Rename farm", "§7==================", "", "You can also use §6/farm rename <name>§f."));
        inventory.setItem(25, createGUIItem(Material.BARRIER, ChatColor.DARK_RED + "Delete farm", "§7==================", "", "You can also use §6/farm delete§f."));
        return inventory;
    }

    private static Inventory initializeFarmRules(Inventory inventory, Farmer farmer){
        Farm farm = farmer.getSelectedFarm();

        inventory.setItem(0, createGUIItem(Material.BOOK, ChatColor.GOLD + "Farm menu"));

        if(farm.getWorld().getGameRuleValue(GameRule.DO_MOB_SPAWNING))
            inventory.setItem(3, createGUIItem(Material.LIME_DYE, ChatColor.RED + "Mob Spawning §2[ENABLED]", "§7==================", "", "Click to §6disable§f mob spawning", "on your farm."));
        else
            inventory.setItem(3, createGUIItem(Material.GRAY_DYE, ChatColor.RED + "Mob Spawning §c[DISABLED]", "§7==================", "", "Click to §6enable§f mob spawning", "on your farm."));

        if(farm.isPublic())
            inventory.setItem(4, createGUIItem(Material.LIME_DYE, ChatColor.RED + "Public Farm §2[ENABLED]", "§7==================", "", "Click to §6privatize§f your farm", "No-one can warp to your farm."));
        else
            inventory.setItem(4, createGUIItem(Material.GRAY_DYE, ChatColor.RED + "Public Farm §c[DISABLED]", "§7==================", "", "Click to §6publish§f your farm", "for others to warp to."));

        inventory.setItem(5, createGUIItem(Material.GRAY_DYE, ChatColor.GREEN + "UNKNOWN IDEA", "§7==================", "", "MAKE A SUGGESTION WHAT", "RULE CAN BE PLACED HERE"));
        inventory.setItem(8, createGUIItem(Material.RED_CONCRETE, ChatColor.RED + "Close"));
        return inventory;
    }

    private static Inventory initializeItemShop(Inventory inventory, Farmer farmer){
        inventory.setItem(0, createGUIItem(Material.BOOK, ChatColor.GOLD + "Farm menu"));
        inventory.setItem(3, createGUIItem(Material.WHEAT, ChatColor.RED + "Buy items", "§7==================", "", "§6Buy§f farm items."));
        inventory.setItem(5, createGUIItem(Material.EMERALD, ChatColor.GREEN + "Sell items", "§7==================", "", "§6Sell§f farm items"));
        inventory.setItem(8, createGUIItem(Material.RED_CONCRETE, ChatColor.RED + "Close"));
        return inventory;
    }

    private static Inventory initializeItemBuy(Inventory inventory, Farmer farmer){
        inventory.setItem(0, createGUIItem(Material.EMERALD, ChatColor.GOLD + "Farm shop"));
        inventory.setItem(8, createGUIItem(Material.RED_CONCRETE, ChatColor.RED + "Close"));

        for(ItemData item : Items.getAll())
            if(item.getItemType().equals(ItemData.ItemType.BUYABLE))
                inventory.setItem(item.getSlot(), createGUIItem(item.getMaterial(), item.getName(), item.getAmount(), item.getLoreWithPrice()));
        return inventory;
    }

    private static Inventory initializeItemSell(Inventory inventory, Farmer farmer){
        inventory.setItem(0, createGUIItem(Material.EMERALD, ChatColor.GOLD + "Farm shop"));
        inventory.setItem(8, createGUIItem(Material.RED_CONCRETE, ChatColor.RED + "Close"));

        for(ItemData item : Items.getAll())
            if(item.getItemType().equals(ItemData.ItemType.SELLABLE))
                inventory.setItem(item.getSlot(), createGUIItem(item.getMaterial(), item.getName(), item.getAmount(), item.getLoreWithPrice()));
        return inventory;
    }
}
