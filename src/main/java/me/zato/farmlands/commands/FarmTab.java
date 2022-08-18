package me.zato.farmlands.commands;

import me.zato.farmlands.farms.Farm;
import me.zato.farmlands.farms.Farms;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class FarmTab implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 2 && args[0].equalsIgnoreCase("warp")) {
            List<String> arguments = new ArrayList<>();
            for(Farm farm : Farms.getList().values())
                if(farm != null)
                    if(farm.isPublic())
                        arguments.add(farm.getName().replace(" ", "_"));
            return arguments;
        }
        if (args.length == 1) {
            List<String> arguments = new ArrayList<>();
            arguments.add("home");
            arguments.add("sethome");
            arguments.add("warp");
            arguments.add("setwarp");
            arguments.add("rename");
            arguments.add("shop");
            arguments.add("rules");
            arguments.add("menu");
            arguments.add("delete");
            arguments.add("help");
            return arguments;
        }

        List<String> arguments = new ArrayList<>();
        arguments.add("");
        return arguments;
    }
}
