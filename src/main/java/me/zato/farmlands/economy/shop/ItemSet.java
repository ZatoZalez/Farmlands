package me.zato.farmlands.economy.shop;

import me.zato.farmlands.Farmlands;

import java.util.ArrayList;

public class ItemSet {
    public static ArrayList<String> getDefaultBuyItems(){
        ArrayList<String> fileLines = new ArrayList<>();

        //default config
        fileLines.add("# BuyItems for " + Farmlands.getPlugin().getDescription().getName() + " " + Farmlands.getPlugin().getDescription().getVersion() + " by ZatoZalez.");
        fileLines.add("# This plugin is custom build for Farmlands.");
        fileLines.add("");

        fileLines.add("1:");
        fileLines.add("  material: FARMLAND");
        fileLines.add("  name: \"&2Farmland\"");
        fileLines.add("  slot: 3");
        fileLines.add("  price: 50");
        fileLines.add("  special: true");
        fileLines.add("  lore:");
        fileLines.add("    - \"Fertile soil to farm on.\"");

        fileLines.add("2:");
        fileLines.add("  material: FARMLAND");
        fileLines.add("  name: \"&432x &2Farmland\"");
        fileLines.add("  slot: 4");
        fileLines.add("  amount: 32");
        fileLines.add("  price: 1600");
        fileLines.add("  special: true");
        fileLines.add("  lore:");
        fileLines.add("    - \"&432 &fFertile soil to farm on.\"");

        fileLines.add("3:");
        fileLines.add("  material: FARMLAND");
        fileLines.add("  name: \"&464x &2Farmland\"");
        fileLines.add("  slot: 5");
        fileLines.add("  amount: 64");
        fileLines.add("  price: 3200");
        fileLines.add("  special: true");
        fileLines.add("  lore:");
        fileLines.add("    - \"&464 &fFertile soil to farm on.\"");

        fileLines.add("4:");
        fileLines.add("  material: WHEAT_SEEDS");
        fileLines.add("  name: \"&2Wheat Seeds\"");
        fileLines.add("  slot: 12");
        fileLines.add("  price: 10");
        fileLines.add("  lore:");
        fileLines.add("    - \"Seeds for a Wheat plant\"");
        fileLines.add("    - \"&6&oGood for farming wheat!\"");

        fileLines.add("5:");
        fileLines.add("  material: WHEAT_SEEDS");
        fileLines.add("  name: \"&432x &2Wheat Seeds\"");
        fileLines.add("  slot: 13");
        fileLines.add("  amount: 32");
        fileLines.add("  price: 320");
        fileLines.add("  lore:");
        fileLines.add("    - \"&432 &fSeeds for a Wheat plant\"");

        fileLines.add("6:");
        fileLines.add("  material: WHEAT_SEEDS");
        fileLines.add("  name: \"&464x &2Wheat Seeds\"");
        fileLines.add("  slot: 14");
        fileLines.add("  amount: 64");
        fileLines.add("  price: 640");
        fileLines.add("  lore:");
        fileLines.add("    - \"&464 &fSeeds for a Wheat plant\"");


        //--
        return fileLines;
    }

    public static ArrayList<String> getDefaultSellItems(){
        ArrayList<String> fileLines = new ArrayList<>();

        //default config
        fileLines.add("# SellItems for " + Farmlands.getPlugin().getDescription().getName() + " " + Farmlands.getPlugin().getDescription().getVersion() + " by ZatoZalez.");
        fileLines.add("# This plugin is custom build for Farmlands.");
        fileLines.add("");

        fileLines.add("1:");
        fileLines.add("  material: FARMLAND");
        fileLines.add("  name: \"&2Farmland\"");
        fileLines.add("  slot: 3");
        fileLines.add("  price: 50");
        fileLines.add("  special: true");
        fileLines.add("  lore:");
        fileLines.add("    - \"Fertile soil to farm on.\"");

        fileLines.add("2:");
        fileLines.add("  material: FARMLAND");
        fileLines.add("  name: \"&432x &2Farmland\"");
        fileLines.add("  slot: 4");
        fileLines.add("  amount: 32");
        fileLines.add("  price: 1600");
        fileLines.add("  special: true");
        fileLines.add("  lore:");
        fileLines.add("    - \"&432 &fFertile soil to farm on.\"");

        fileLines.add("3:");
        fileLines.add("  material: FARMLAND");
        fileLines.add("  name: \"&464x &2Farmland\"");
        fileLines.add("  slot: 5");
        fileLines.add("  amount: 64");
        fileLines.add("  price: 3200");
        fileLines.add("  special: true");
        fileLines.add("  lore:");
        fileLines.add("    - \"&464 &fFertile soil to farm on.\"");

        fileLines.add("4:");
        fileLines.add("  material: WHEAT");
        fileLines.add("  name: \"&2Wheat\"");
        fileLines.add("  slot: 12");
        fileLines.add("  price: 10");
        fileLines.add("  lore:");
        fileLines.add("    - \"Sell Wheat\"");
        fileLines.add("    - \"Good for your balance!\"");

        fileLines.add("5:");
        fileLines.add("  material: WHEAT");
        fileLines.add("  name: \"&432x &2Wheat\"");
        fileLines.add("  slot: 13");
        fileLines.add("  price: 320");
        fileLines.add("  amount: 32");
        fileLines.add("  lore:");
        fileLines.add("    - \\\"Sell &432 &fWheat\\\"\"");

        fileLines.add("6:");
        fileLines.add("  material: WHEAT");
        fileLines.add("  name: \"&464x &2Wheat\"");
        fileLines.add("  slot: 14");
        fileLines.add("  price: 640");
        fileLines.add("  amount: 64");
        fileLines.add("  lore:");
        fileLines.add("    - \"Sell &464 &fWheat\"");


        //--
        return fileLines;
    }
}
