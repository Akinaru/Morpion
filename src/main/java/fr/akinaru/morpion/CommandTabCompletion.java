package fr.akinaru.morpion;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.*;

public class CommandTabCompletion implements TabCompleter {

    private List<String> getAllMPlayers() {
        List<String> list = new ArrayList<String>();
        for(Player players : Bukkit.getOnlinePlayers()) {
            list.add(players.getName());
        }
        list.add("accept");

        return list;
    }

    private List<String> getContainedMaterials(String players) {
        List<String> list = new ArrayList<String>();
        for(String mat : getAllMPlayers()) {
            if(mat.startsWith(players.toUpperCase())) {
                list.add(mat);
            }
        }
        return list;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 2) {
            List<String> list = getContainedMaterials(args[1]);
            return list;
        }
        return null;
    }
}
