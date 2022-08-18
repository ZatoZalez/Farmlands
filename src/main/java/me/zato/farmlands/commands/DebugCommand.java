package me.zato.farmlands.commands;

import me.zato.farmlands.communication.Messages;
import me.zato.farmlands.communication.Messenger;
import me.zato.farmlands.farmers.Farmers;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DebugCommand implements CommandExecutor {
    @Override
    public boolean onCommand (CommandSender sender, Command command, String string, String []args){
        if (!(sender instanceof Player))
            return false;

        Player player = ((Player) sender).getPlayer();
        if(!player.isOp())
            return true;

        if(!Farmers.isFarmer(player))
            return true;

        if(args.length > 0){
            if(Bukkit.getWorld(args[0]) == null){
                Messenger.write(player, Messages.debug_invalid_world);
                return true;
            }
            WorldCreator wc = new WorldCreator(args[0]);
            World world = Bukkit.createWorld(wc);
            player.teleport(world.getSpawnLocation());
        }
        return true;
    }
}