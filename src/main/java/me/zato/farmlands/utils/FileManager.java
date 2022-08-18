package me.zato.farmlands.utils;

import me.zato.farmlands.Farmlands;
import me.zato.farmlands.communication.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import static org.bukkit.Bukkit.getServer;

public class FileManager {
    private static String dataFolderDirectory = Farmlands.getPlugin().getDataFolder().getAbsolutePath();
    private static String defaultFarmWorldDirectory = Farmlands.getPlugin().getDataFolder().getAbsolutePath() + "/defaultfarmworld";
    private static String farmWorldsDirectory = "farmworlds";
    private static String bukkitYMLDirectory = "bukkit.yml";
    private static String farmersDirectory = Farmlands.getPlugin().getDataFolder().getAbsolutePath() + "/farmers";

    private static String farmsDirectory = Farmlands.getPlugin().getDataFolder().getAbsolutePath() + "/farms";
    private static String defaultFarmWorldWorldDirectory = Farmlands.getPlugin().getDataFolder().getAbsolutePath() + "/defaultfarmworld/world";
    private static boolean routineFailed = false;

    public static void routineCheck(){
        if(!createDirectory(dataFolderDirectory))
            throwingError(MessageFormat.format("Something went wrong creating the DataFolder; could not find {0}.", dataFolderDirectory));
        if(!createDirectory(defaultFarmWorldDirectory))
            throwingError(MessageFormat.format("Something went wrong creating the defaultFarmWorld directory; could not find {0}.", defaultFarmWorldDirectory));
        if(!createDirectory(farmWorldsDirectory))
            throwingError(MessageFormat.format("Something went wrong creating the farmWorlds worlds-container; could not find {0}.", farmWorldsDirectory));
        if(!createDirectory(farmersDirectory))
            throwingError(MessageFormat.format("Something went wrong creating the farmers directory; could not find {0}.", farmersDirectory));
        if(!createDirectory(farmsDirectory))
            throwingError(MessageFormat.format("Something went wrong creating the farms directory; could not find {0}.", farmsDirectory));
        if(!checkDirectory(defaultFarmWorldWorldDirectory))
            throwingWarning(MessageFormat.format("There is no defaultFarmWorld found, please make sure to place the world data in {0}.", defaultFarmWorldWorldDirectory));
        if(!editBukkitSettings(bukkitYMLDirectory))
            throwingError(MessageFormat.format("Could not edit the bukkit.yml. Please manually add 'world-container: FarmWorlds' under 'settings:' in the bukkit.yml file.", bukkitYMLDirectory));

        if(routineFailed){
            Logger.writeError("Disabling due to error. Some directories or files were not created properly.");
            getServer().getPluginManager().disablePlugin(Farmlands.getPlugin());
        }
    }

    public static String getFarmWorldsDirectory() {
        return farmWorldsDirectory;
    }

    public static String getDefaultFarmWorldWorldDirectory() {
        return defaultFarmWorldWorldDirectory;
    }

    private static boolean createDirectory(String dir){
        File file = new File(dir);
        if(!file.exists())
            return file.mkdir();
        return true;
    }

    private static boolean checkDirectory(String dir){ return (new File(dir)).exists(); }

    private static boolean editBukkitSettings(String dir) {
        File file = new File(dir);
        if (!file.exists())
            return false;

        try {
            boolean implemented = false;
            List<String> fileLines = new ArrayList<>();
            List<String> newLines = new ArrayList<>();
            fileLines = Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);
            for(int i = 0; i < fileLines.size(); i++)
                if(fileLines.get(i).contains("world-container:")){
                    fileLines.set(i, "  world-container: " + farmWorldsDirectory);
                    implemented = true;
                    newLines = fileLines;
                    break;
                }

            if(!implemented)
            for(int i = 0; i < fileLines.size(); i++) {
                newLines.add(fileLines.get(i));
                if (fileLines.get(i).contains("settings:")) {
                    newLines.add("  world-container: " + farmWorldsDirectory);
                    implemented = true;
                }
            }

            String data = "";
            for(String s : newLines){
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
        } catch (IOException e) {
            throwingError(e.getMessage());
            return false;
        }
        return true;
    }

    private static void throwingWarning(String warning){ Logger.writeWarning(warning); }

    private static void throwingError(String error){ Logger.writeError(error); routineFailed = true; }

    public static boolean worldExists(String worldid){
        return (new File(farmWorldsDirectory + "/" + worldid)).exists();
    }

    public static int getIntFromString(String input){
        try {
            int i = Integer.parseInt(input);
            return i;
        }
        catch (NumberFormatException e) {
            return 0;
        }
    }

    public static double getDoubleFromString(String input){
        try {
            double d = Double.parseDouble(input);
            return d;
        }
        catch (NumberFormatException e) {
            return 0;
        }
    }

    public static boolean getBoolFromString(String input){
        return getBoolFromString(input, false);
    }

    public static boolean getBoolFromString(String input, boolean catchOutput){
        try {
            boolean b = Boolean.parseBoolean(input);
            return b;
        }
        catch (Exception e) {
            return catchOutput;
        }
    }
}
