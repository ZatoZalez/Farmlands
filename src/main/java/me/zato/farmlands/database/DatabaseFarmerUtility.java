package me.zato.farmlands.database;

import me.zato.farmlands.Farmlands;
import me.zato.farmlands.communication.Logger;
import me.zato.farmlands.farmers.Farmer;
import me.zato.farmlands.farmers.Farmers;
import me.zato.farmlands.utils.FileManager;
import org.bukkit.ChatColor;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class DatabaseFarmerUtility {

    private static String filePath = Farmlands.getPlugin().getDataFolder().getAbsolutePath() + "/farmers/";

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
            {
                if(readFile(file))
                    count++;
            }
        }

        if(count > 0)
            Logger.write(ChatColor.RED + "" + count + ChatColor.GRAY + " Farmer(s) have been loaded.");
    }

    public static void save(){
        if(Farmers.getList() != null && Farmers.getList().size() > 0)
            for (Farmer farmer : Farmers.getList().values())
                if(farmer != null)
                    saveFarmer(farmer);
    }

    private static void saveFarmer(Farmer farmer){
        File file = new File(filePath + farmer.getId() + ".json");

        try {
            file.createNewFile();
            String data = farmer.getInline();
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
            if (!map.containsKey("playerid"))
                return false;

            Farmer farmer = new Farmer(file.getName().split("[.]")[0], map.get("playerid"));

            if (map.containsKey("defaultfarm"))
                farmer.setDefaultFarm(map.get("defaultfarm"));

            //balance
            if (map.containsKey("balance"))
                farmer.setBalance(FileManager.getDoubleFromString(map.get("balance")));

            //farmland
            if (map.containsKey("farmland"))
                farmer.setFarmland(FileManager.getIntFromString(map.get("farmland")));

            //farms
            if (map.containsKey("farms")) {
                String[] farms = map.get("farms").split(",");
                if(farms.length > 0)
                    for (String farm : farms)
                        if (farm.trim().length() > 5)
                            farmer.addFarm(farm);
            }

            Farmers.add(farmer);
            return true;
        }catch(Exception e) {
            Logger.writeError("Could not read " + file.getAbsolutePath() +  ". Skipping this farmer.\n" + e.getMessage());
        }
        return false;
    }
}
