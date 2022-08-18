package me.zato.farmlands.gui;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class GUIHolder implements InventoryHolder {

    public enum TYPE{
        FARMS,
        FARM_SHOP,
        FARM_MENU,
        FARM_RULES,
        ITEM_SHOP,
        ITEM_SELL,
        ITEM_BUY
    }
    public TYPE type;
    public GUIHolder(TYPE type){
        this.type = type;
    }

    @Override
    public Inventory getInventory() {
        return null;
    }
}
