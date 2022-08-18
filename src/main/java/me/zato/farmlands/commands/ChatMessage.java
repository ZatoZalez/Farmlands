package me.zato.farmlands.commands;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;

import java.util.HashMap;

public class ChatMessage {
    private static HashMap<Player, TYPE> awaitingMessages = new HashMap<>();
    public enum TYPE{
        RENAME
    }
    public static void playerMessage(PlayerChatEvent e){
        String text = e.getMessage();
        Player player = e.getPlayer();
        if(player == null || text == null)
            return;

        if(!isAwaitingMessage(player))
            return;

        switch(awaitingMessages.get(player)){
            case RENAME:{
                FarmCommand.rename(player, text);
                awaitingMessages.remove(player);
                e.setCancelled(true);
                break;
            }
        }
    }

    public static void addAwaitingMessage(Player player, TYPE type){
        if(isAwaitingMessage(player))
            awaitingMessages.remove(player);
        awaitingMessages.put(player, type);
    }

    private static boolean isAwaitingMessage(Player player){
        if(awaitingMessages != null && awaitingMessages.size() > 0)
            if(awaitingMessages.containsKey(player))
                return true;
        return false;
    }

}
