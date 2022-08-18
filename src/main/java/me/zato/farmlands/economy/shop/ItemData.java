package me.zato.farmlands.economy.shop;

import me.zato.farmlands.economy.Utils;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class ItemData {

    public enum ItemType{
        SELLABLE,
        BUYABLE
    }

    private ItemType itemType;
    private Material material;
    private String name;
    private int amount = 1;
    private int slot = 0;
    private double price = 0;
    private boolean special = false;
    private String[] lore;

    public ItemData(ItemType itemType, String name){
        this.itemType = itemType;
        this.name = name;
    }

    public boolean isValid() {
        if(itemType == null || material == null || name == null)
            return false;
        if(name.length() < 1 || amount >= 0 || amount <= 64 || slot >= 0 || slot <= 45)
            return false;
        return true;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public String getName() {
        return "§r§f" + name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String[] getLore() {
        List list = new ArrayList<>();
        if(lore.length > 0)
            for(String s : lore)
                list.add("§r§f" + s);
        return (String[]) list.toArray(new String[0]);
    }

    public String[] getLoreWithPrice() {
        List list = new ArrayList<>();
        list.add("§r§f$" + Utils.convertToVisualValue(getPrice()));
        list.add("");
        if(lore.length > 0)
            for(String s : lore)
                list.add("§r§f" + s);
        return (String[]) list.toArray(new String[0]);
    }

    public void setLore(String[] lore) {
        this.lore = lore;
    }

    public boolean isSpecial() {
        return special;
    }

    public void setSpecial(boolean special) {
        this.special = special;
    }
}
