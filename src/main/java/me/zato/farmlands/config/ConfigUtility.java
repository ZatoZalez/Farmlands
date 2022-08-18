package me.zato.farmlands.config;

import me.zato.farmlands.Farmlands;
import me.zato.farmlands.communication.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ConfigUtility {

    private static ArrayList<ConfigData> configData = new ArrayList<>();
    private static String configPath = Farmlands.getPlugin().getDataFolder().getAbsolutePath();
    private static String configFile = "config.yml";

    public static void build()  {
        try {
            create();
        }catch(Exception e) {
            Logger.writeError("Could not create " + configFile + ".\n" + e.getMessage());
        }
    }

    private static void create() throws IOException {
        try {
            String data = "";
            File file = new File(configPath + "/" + configFile);
            file.getParentFile().mkdir();
            ArrayList<String> fileLines = ConfigSet.getDefaultConfig();

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
            Logger.writeError("Could not create " + configFile + ".\n" + e.getMessage());
        }
    }

    public static void read(){
        File file = new File(configPath + "/" + configFile);
        if(!file.exists()){
            build();
            return;
        }

        ArrayList<ConfigData> tempData = new ArrayList<>();
        try
        {
            List<String> fileLines = new ArrayList<>();
            fileLines = Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);
            if(fileLines.size() < 1){
                build();
                return;
            }
            for(String s : fileLines){
                if(s.equals(null) || s.equals("") || s.length() < 3 || !s.contains(":") || s.startsWith("#"))
                    continue;

                String key = s.split(":")[0].trim();
                String value = s.split(":", 2)[1].trim();
                if(key.contains("#"))
                    key = key.split("#")[0].trim();
                if(value.contains("#"))
                    value = value.split("#")[0].trim();
                tempData.add(new ConfigData(key, value));
            }
        }catch(Exception e) {
            Logger.writeError("Could not create " + configFile + ".\n" + e.getMessage());
            return;
        }

        if(tempData.size() < 1)
            Logger.writeError("The " + configFile + " could not be read. Creating new config...");
        configData = tempData;
        build();
    }

    public static void save(){
        File file = new File(configPath + "/" + configFile);
        if(!file.exists()){
            build();
            return;
        }

        try {
            String data = "";
            ArrayList<String> fileLines = new ArrayList<>();
            for(ConfigData cd : configData)
                if(data.equalsIgnoreCase(""))
                    data += cd.getInline();
                else
                    data += "\n" + cd.getInline();
            file.createNewFile();
            Writer writer = new FileWriter(file, false);
            writer.write(data);
            writer.flush();
            writer.close();
        }catch(Exception e) {
            Logger.writeError("Could not write to " + configFile + "\n" + e.getMessage());
            return;
        }
    }

    public static ConfigData addData(String key, Object value){
        return addData(key, value, null);
    }

    public static ConfigData addData(String key, Object value, String comment){
        for(ConfigData cd : configData)
            if(cd.isValid() && cd.getKey().equalsIgnoreCase(key))
                return cd;

        ConfigData data = new ConfigData(key, value.toString());
        if(comment != null)
            data.setComment(comment);

        configData.add(data);
        return data;
    }

    public static ConfigData addData(String description){
        for(ConfigData cd : configData)
            if(cd.isDescription() && cd.getDescription().equalsIgnoreCase(description))
                return cd;
        ConfigData data = new ConfigData(description);
        configData.add(data);
        return data;
    }
    public static ConfigData getData(String key){
        for(ConfigData data : configData)
            if(data.isValid() && data.getKey().equalsIgnoreCase(key))
                return data;
        return null;
    }

    @Deprecated
    public static void removeData(String key){
        for(ConfigData cd : configData)
            if(cd.isValid() && cd.getKey().equalsIgnoreCase(key)){
                configData.remove(cd);
                return;
            }
        return;
    }
}


