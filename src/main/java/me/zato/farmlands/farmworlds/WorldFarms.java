package me.zato.farmlands.farmworlds;

import me.zato.farmlands.communication.Logger;
import me.zato.farmlands.communication.Messenger;
import me.zato.farmlands.farms.Farm;
import me.zato.farmlands.utils.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class WorldFarms {
    private static String prefixWorldName = "farmworld_";
    private static LinkedHashMap<UUID, World> worlds = new LinkedHashMap<>();

    public static LinkedHashMap<UUID, World> getWorlds(){
        return worlds;
    }

    public static boolean worldExists(String worldname){
        if(worldname == null)
            return false;
        File world = new File(Bukkit.getServer().getWorldContainer(), worldname.toString());
        return (world.exists());
    }

    public static World setWorldGameRules(WorldCreator wc){
        World world = Bukkit.getServer().createWorld(wc);
        world.setGameRule(GameRule.RANDOM_TICK_SPEED, 20);
        world.setGameRule(GameRule.KEEP_INVENTORY, true);
        world.setGameRule(GameRule.DO_MOB_SPAWNING, false);
        return world;
    }

    public static Object[] createFarmWorld(Player p) {
        Logger.write("Generating new farm for " + p.getDisplayName() + ".");

        UUID uuid = UUID.randomUUID();
        String worldname = uuid.toString();
        copyDefaultWorldToFarmWorlds(worldname);

        if(!FileManager.worldExists(worldname)){
            Logger.writeError("Could not find world directory '" + FileManager.getFarmWorldsDirectory() + "/" + worldname + "'");
            return null;
        }

        WorldCreator wc = new WorldCreator(worldname);
        if(wc == null)
        {
            Logger.writeError("World creator is null for world '" + worldname + "'");
            return null;
        }

        World world = setWorldGameRules(wc);
        if(world == null)
        {
            Logger.writeError("World is null with world '" + worldname + "'");
            return null;
        }

        Logger.write("Generating farm for " + p.getDisplayName() + "...");;
        worlds.put(world.getUID(), world);
        return new Object[] { world.getUID(), worldname };
    }

    private static void copyDefaultWorldToFarmWorlds(String worldname){
        try {
            final Path fromPath = Paths.get(FileManager.getDefaultFarmWorldWorldDirectory());
            Files.walk(fromPath)
                    .forEach(source -> moveDirectories(fromPath, source, worldname));
        } catch (IOException e) {
            Logger.writeError("Could not walk files from source to target directory.\n" + e);
        }
    }

    private static void moveDirectories(Path fromPath, Path source, String worldname){
        Path destination = Paths.get(FileManager.getFarmWorldsDirectory() + "/" + worldname, source.toString()
                .substring(fromPath.toString()
                        .length()));
        try {
            Files.copy(source, destination);

        } catch (IOException e) {
            Logger.writeError("Could not move this file/subdirectory from source to target directory.\n" + e);
        }
    }

    public static void unloadWorld(Farm farm){
        if(!farm.getWorld().equals(null)) {
            Bukkit.getServer().unloadWorld(farm.getWorld(), true);
        }
    }

    public static void deleteWorld(Farm farm){
        File deleteFolder = farm.getWorld().getWorldFolder();
        deleteWorld(deleteFolder);
    }

    public static void deleteWorld(File file) {
        File[] contents = file.listFiles();
        if (contents != null) {
            for (File f : contents) {
                deleteWorld(f);
            }
        }
        file.delete();
    }
}
