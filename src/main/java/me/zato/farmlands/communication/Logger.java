package me.zato.farmlands.communication;

import me.zato.farmlands.Farmlands;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Logger {
    public static void write(String data){
        Bukkit.getConsoleSender().sendMessage("[" + Farmlands.getPlugin().getDescription().getName() + "] " + data);
    }

    public static void writeWarning(String data){
        Bukkit.getConsoleSender().sendMessage("[" + Farmlands.getPlugin().getDescription().getName() + "] " + ChatColor.GOLD + "[WARNING] " + data);
    }

    public static void writeError(String data){
        Bukkit.getConsoleSender().sendMessage("[" + Farmlands.getPlugin().getDescription().getName() + "] " + ChatColor.RED + "[ERROR] " + data);
    }
}
