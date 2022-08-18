package me.zato.farmlands.economy.shop;

import me.zato.farmlands.communication.Logger;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;
import java.util.stream.Collectors;

public class Items {
    private static List<ItemData> items = new ArrayList<>();

    public static void add(ItemData item){
        if(!items.contains(item))
            items.add(item);
    }

    public static List<ItemData> getAll(){
        return items;
    }

    public static ItemData getItem(ItemData.ItemType itemtype, String itemname){
        if(items == null || items.size() < 1)
            return null;
        for(ItemData item : items)
            if(item != null)
                if (item.getItemType().equals(itemtype) && ChatColor.stripColor(item.getName()).equals(ChatColor.stripColor(itemname)))
                    return item;
        return null;
    }

    public static ItemStack createGUIItem(final Material material, final String name, final String... lore) {
        return createGUIItem(material, name, 1, lore);
    }

    public static ItemStack createGUIItem(final Material material, final String name, final int amount, final String... lore) {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();
        item.setAmount(amount);
        meta.setDisplayName(name);

        List<String> newLore = Arrays.stream(lore)
                .map(s -> "§f" + s)
                .collect(Collectors.toList());
        meta.setLore(newLore);
        item.setItemMeta(meta);
        return item;
    }
}
