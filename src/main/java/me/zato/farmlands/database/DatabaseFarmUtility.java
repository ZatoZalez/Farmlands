package me.zato.farmlands.database;

import me.zato.farmlands.Farmlands;
import me.zato.farmlands.communication.Logger;
import me.zato.farmlands.farms.Farm;
import me.zato.farmlands.farms.Farms;
import me.zato.farmlands.utils.FileManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class DatabaseFarmUtility {

    private static String filePath = Farmlands.getPlugin().getDataFolder().getAbsolutePath() + "/farms/";

    private static void create() {
        try {
            if(!Files.isDirectory(Paths.get(filePath)))
                Files.createDirectories(Paths.get(filePath));
        } catch(Exception e) {
            Logger.writeError("Could not create directory " + filePath +  "\n" + e.getMessage());
        }
    }

    public static void read(){
        create();

        int count = 0;
        File directoryPath = new File(filePath);
        File filesList[] = directoryPath.listFiles();
        for(File file : filesList) {
            if(file != null && file.exists() && file.getName().contains("."))
                if(readFile(file))
                    count++;
        }

        if(count > 0)
            Logger.write(ChatColor.RED + "" + count + ChatColor.GRAY + " Farm(s) have been loaded.");
    }

    public static void save(){
        if(Farms.getList() != null && Farms.getList().size() > 0)
            for (Farm farm : Farms.getList().values())
                if(farm != null)
                    saveFarm(farm);
    }

    private static void saveFarm(Farm farm){
        File file = new File(filePath + farm.getId() + ".json");

        try {
            file.createNewFile();
            String data = farm.getInline();
            Writer writer = new FileWriter(file, false);
            writer.write(data);
            writer.flush();
            writer.close();
        }catch(Exception e) {
            Logger.writeError("Could not save to " + file.getAbsolutePath() +  "\n" + e.getMessage());
            return;
        }
    }

    private static boolean readFile(File file){
        try {
            List<String> lines = new ArrayList<>();
            lines = Files.readAllLines(file.getAbsoluteFile().toPath(), StandardCharsets.UTF_8);
            if (lines == null || lines.size() < 1)
                return false;

            Map<String, String> map = new HashMap<String, String>();
            for (String s : lines) {
                if (s.equals(null) || s.equals("") || s.length() < 3 || s.startsWith("#") || !s.contains(":"))
                    continue;

                String key = s.split(":")[0].trim().toLowerCase();
                String value = s.split(":", 2)[1].trim();
                map.put(key, value);
            }

            if (!map.containsKey("worldid") || !map.containsKey("worldname") || !map.containsKey("farmerid") || !map.containsKey("name"))
                return false;

            Farm farm = new Farm(file.getName().split("[.]")[0], map.get("worldid"), map.get("worldname"), map.get("farmerid"), map.get("name"));

            //bordersize
            if (map.containsKey("bordersize"))
                farm.setBordersize(FileManager.getIntFromString(map.get("bordersize")));

            //value
            if (map.containsKey("value"))
                farm.setValue(FileManager.getIntFromString(map.get("value")));

            //ispublic
            if (map.containsKey("ispublic"))
                farm.isPublic(FileManager.getBoolFromString(map.get("ispublic")));

            //centerpoint
            if (map.containsKey("centerpoint")){
                String[] coords = map.get("centerpoint").split(",");
                if(coords.length == 3) {
                    Location location = new Location(farm.getWorld(), FileManager.getDoubleFromString(coords[0]), FileManager.getDoubleFromString(coords[1]), FileManager.getDoubleFromString(coords[2]));
                    farm.setCenterPoint(location);
                }
            }

            //home
            if (map.containsKey("home")){
                String[] coords = map.get("home").split(",");
                if(coords.length == 3) {
                    Location location = new Location(farm.getWorld(), FileManager.getDoubleFromString(coords[0]), FileManager.getDoubleFromString(coords[1]), FileManager.getDoubleFromString(coords[2]));
                    farm.setHome(location);
                }
            }

            //warp
            if (map.containsKey("warp")){
                String[] coords = map.get("warp").split(",");
                if(coords.length == 3) {
                    Location location = new Location(farm.getWorld(), FileManager.getDoubleFromString(coords[0]), FileManager.getDoubleFromString(coords[1]), FileManager.getDoubleFromString(coords[2]));
                    farm.setWarp(location);
                }
            }

            Farms.add(farm);
            return true;
        }catch(Exception e) {
            Logger.writeError("Could not read " + file.getAbsolutePath() +  ". Skipping this farm.\n" + e.getMessage());
        }
        return false;
    }
}
