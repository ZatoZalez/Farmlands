package me.zato.farmlands.economy.shop;

import me.zato.farmlands.Farmlands;
import me.zato.farmlands.communication.Logger;
import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemUtility {
    private static String itemPath = Farmlands.getPlugin().getDataFolder().getAbsolutePath();
    private static String buyItemsFile = "buyitems.yml";
    private static String sellItemsFile = "sellitems.yml";

    public static void build()  {
        try {
            create(buyItemsFile, ItemSet.getDefaultBuyItems());
        }catch(Exception e) {
            Logger.writeError("Could not create " + buyItemsFile + ".\n" + e.getMessage());
        }

        try {
            create(sellItemsFile, ItemSet.getDefaultSellItems());
        }catch(Exception e) {
            Logger.writeError("Could not create " + sellItemsFile + ".\n" + e.getMessage());
        }
    }

    private static void create(String itemsFile, ArrayList<String> list) throws IOException {
        try {
            String data = "";
            File file = new File(itemPath + "/" + itemsFile);
            file.getParentFile().mkdir();
            ArrayList<String> fileLines = list;

            for(String s : fileLines){
                if(data.equalsIgnoreCase(""))
                    data += s;
                else
                    data += "\n" + s;
            }
            file.createNewFile();
            Writer writer = new FileWriter(file, false);
            writer.write(data);
            writer.flush();
            writer.close();
        }catch(Exception e) {
            Logger.writeError("Could not create " + itemsFile + ".\n" + e.getMessage());
        }
    }

    public static void read(){
        read(buyItemsFile, ItemData.ItemType.BUYABLE, ItemSet.getDefaultBuyItems());
        read(sellItemsFile, ItemData.ItemType.SELLABLE, ItemSet.getDefaultSellItems());
        if(Items.getAll() != null && Items.getAll().size() > 0)
            Logger.write(ChatColor.RED + "" + Items.getAll().size() + ChatColor.GRAY + " Item(s) have been loaded.");
    }

    private static void read(String itemsFile, ItemData.ItemType itemType, ArrayList<String> list){
        File file = new File(itemPath + "/" + itemsFile);
        if(!file.exists()){
            try {
                create(itemsFile, list);
                read(itemsFile, itemType, list);
            }catch(Exception e) {
                Logger.writeError("Could not create " + itemsFile + ".\n" + e.getMessage());
            }
            return;
        }

        try
        {
            HashMap<Integer, List<String>> map = new HashMap<Integer, List<String>>();
            List<String> fileLines = new ArrayList<>();

            fileLines = Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);
            if(fileLines.size() < 1){
                build();
                return;
            }

            int key = 0;
            List<String> attributesList = new ArrayList<>();
            for(String s : fileLines){
                //detect new item
                if(key != 0 && !s.startsWith("  ") && s.endsWith(":")){
                    map.put(key, attributesList);
                    attributesList = new ArrayList<>();
                    key = 0;
                }

                //ignore lines with no valid key
                if(key == 0 && s.startsWith("  "))
                    continue;

                //ignore invalid lines
                if(s.equals(null) || s.equals("") || s.startsWith("#"))
                    continue;

                //detect item
                if(key == 0 && !s.startsWith("  ") && s.endsWith(":")) {
                    try {
                        s = s.substring(0, s.length() - 1);
                        key = Integer.parseInt(s);
                    } catch (Exception e) {
                        Logger.writeWarning("Could not read an item in " + itemsFile + ". Skipping this item.\n" + e.getMessage());
                    }
                    continue;
                }

                //add attributes to key
                attributesList.add(s);
            }
            map.put(key, attributesList);
            for(int k: map.keySet()) {
                boolean isLore = false;
                List<String> loreList = new ArrayList<>();
                HashMap<String, String> inmap = new HashMap<String, String>();
                for(String s : map.get(k)){

                    if(s.equals(null) || s.equals("") || s.length() < 3 || (!s.contains(":") && !s.startsWith("    -")) || s.startsWith("#"))
                        continue;

                    s = s.replace("&", "§");
                    String keyname = "";
                    String value = "";
                    if(s.contains(":")) {
                        keyname = s.split(":")[0].trim();
                        value = s.split(":", 2)[1].trim();
                        if (keyname.contains("#"))
                            keyname = keyname.split("#")[0].trim();
                        if (value.contains("#"))
                            value = value.split("#")[0].trim();
                        if (value.startsWith("\"") && value.endsWith("\""))
                            value = value.substring(1, value.length() - 1);

                        if(keyname.equalsIgnoreCase("lore"))
                        {
                            isLore = true;
                            inmap.put(keyname, value);
                            continue;
                        }
                    }

                    if(isLore)
                        if(s.startsWith("    -"))
                        {
                            value = s.substring(5).trim();
                            if (value.startsWith("\"") && value.endsWith("\""))
                                value = value.substring(1, value.length() - 1);
                            loreList.add(value);
                        }
                        else
                            isLore = false;
                    else
                        inmap.put(keyname, value);
                }

                if(!inmap.containsKey("name"))
                {
                    Logger.writeWarning("Item " + k + " in " + itemsFile + " does not have a name. Skipping this item.");
                    continue;
                }

                ItemData itemData = new ItemData(itemType, inmap.get("name"));
                for(String keyname : inmap.keySet()){
                    String value = inmap.get(keyname);
                    if(value == null)
                        continue;
                    switch(keyname){
                        case "material":{
                            Material material = Material.valueOf(value);
                            itemData.setMaterial(material);
                            break;
                        }
                        case "slot":{
                            int i = 0;
                            try {
                                i = Integer.parseInt(value);
                            }catch (Exception e){  }
                            itemData.setSlot(i);
                            break;
                        }
                        case "price":{
                            int i = 0;
                            try {
                                i = Integer.parseInt(value);
                            }catch (Exception e){  }
                            itemData.setPrice(i);
                            break;
                        }
                        case "amount":{
                            int i = 0;
                            try {
                                i = Integer.parseInt(value);
                            }catch (Exception e){  }
                            itemData.setAmount(i);
                            break;
                        }
                        case "lore":{
                            if(loreList.size() > 0)
                                itemData.setLore(loreList.toArray(new String[0]));
                            break;
                        }
                        case "special":{
                            boolean b = false;
                            try {
                                b = Boolean.parseBoolean(value.toLowerCase());
                            }catch (Exception e){  }
                            itemData.setSpecial(b);
                            break;
                        }
                    }
                }
                if(itemData.isValid()){
                    Logger.writeWarning("Item " + k + " in " + itemsFile + " does not meet the minimal requirements for an item. Skipping this item.");
                    continue;
                }
                Items.add(itemData);
            }
        }catch(Exception e) {
            Logger.writeError("Could not create " + itemsFile + ".\n" + e.getMessage());
            return;
        }
    }
}
