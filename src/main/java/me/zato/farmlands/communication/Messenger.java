package me.zato.farmlands.communication;

import me.zato.farmlands.farmers.Farmers;
import org.bukkit.entity.Player;

public class Messenger {
    public static String PREFIX = "§l§2F§aa§2r§am§2l§aa§2n§ad§2s §2▶ §r§f";


    public static void write(Player player, String message){
        write(player, message, true);
    }
    public static void write(Player player, String message, boolean prefix){
        if(prefix)
            message = PREFIX + message;
        player.sendMessage(filterPlaceholders(player, message));
    }

    public static String filterPlaceholders(Player player, String message){
        if(message == null)
            return null;
        if(player != null)
            message = message.replace("${PLAYER}", player.getDisplayName());
        return message;
    }
}
