package fr.akinaru.morpion;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;

public class Game implements Listener {



    public static HashMap<Player, Player> Jeu = new HashMap<Player, Player>();
    public static HashMap<Player, Player> LastAsker = new HashMap<Player, Player>();
    public static HashMap<Player, Player> JeuTour = new HashMap<Player, Player>();

    public static void startGame(Player player1, Player player2){
        InventoryManager.showInventory(player1);
        InventoryManager.showInventory(player2);
    }

    public static void changeTour(Player player){
        Player adversaire = (Player) Jeu.get(player);
        if(JeuTour.get(player).equals(player)){
            JeuTour.put(player, adversaire);
            JeuTour.put(adversaire, adversaire);
        }else{
            JeuTour.put(player, player);
            JeuTour.put(adversaire, player);
        }
    }

    public static void PoserPion(Player player, InventoryClickEvent e) {
        if(e.getSlot() == 12 ||
        e.getSlot() == 13 ||
        e.getSlot() == 14 ||
        e.getSlot() == 21 ||
        e.getSlot() == 22 ||
        e.getSlot() == 23 ||
        e.getSlot() == 30 ||
        e.getSlot() == 31 ||
        e.getSlot() == 32){
            Game.changeTour(player);
            //pose pion
        }
    }
}
