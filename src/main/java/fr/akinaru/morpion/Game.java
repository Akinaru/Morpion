package fr.akinaru.morpion;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;

public class Game implements Listener {



    public static HashMap<Player, Player> Jeu = new HashMap<Player, Player>();
    public static HashMap<Player, Player> LastAsker = new HashMap<Player, Player>();

    public static void startGame(Player player1, Player player2){
        InventoryManager.showInventory(player1);
        InventoryManager.showInventory(player2);
    }

}
