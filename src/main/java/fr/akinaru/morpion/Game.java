package fr.akinaru.morpion;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;

public class Game implements Listener {



    public static HashMap<Player, Player> JeuAdversaire = new HashMap<Player, Player>();
    public static HashMap<Player, Integer> JeuNumeroJoueur = new HashMap<Player, Integer>();
    public static HashMap<Player, Player> LastAsker = new HashMap<Player, Player>();
    public static HashMap<Player, Player> JeuTour = new HashMap<Player, Player>();
    public static HashMap<Player, Inventory> InventaireJoueur = new HashMap<Player, Inventory>();
    public static Table<Player, Integer, Player> JeuEnplacementPions = HashBasedTable.create();




    public static void startGame(Player player1, Player player2){
        Game.JeuTour.put(player1, player1);
        Game.JeuTour.put(player2, player1);
        Game.JeuAdversaire.put(player1, player2);
        Game.JeuAdversaire.put(player2, player1);
        Game.LastAsker.remove(player1);
        Game.LastAsker.remove(player2);
        Game.JeuNumeroJoueur.put(player1, 1);
        Game.JeuNumeroJoueur.put(player2, 2);
        InventoryManager.OpenInventory(player1);
        InventoryManager.OpenInventory(player2);

    }

    public static void changeTour(Player player){
        Player adversaire = (Player) JeuAdversaire.get(player);
        if(JeuTour.get(player).equals(player)){
            JeuTour.put(player, adversaire);
            JeuTour.put(adversaire, adversaire);
        }else{
            JeuTour.put(player, player);
            JeuTour.put(adversaire, player);
        }
    }

    public static void PoserPion(Player player, InventoryClickEvent e) {
        Player adversaire = (Player) Game.JeuAdversaire.get(player);
        JeuEnplacementPions.put(player, e.getSlot(), player);
        JeuEnplacementPions.put(adversaire, e.getSlot(), player);
        player.sendMessage("Tu as posé un pion sur le slot "+e.getSlot());
        adversaire.sendMessage("le slot "+e.getSlot()+" appartient à "+JeuEnplacementPions.get(player, e.getSlot()));
        Game.changeTour(player);
        Game.InventaireJoueur.remove(player);
        Game.InventaireJoueur.remove(adversaire);
        InventoryManager.OpenInventory(player);
        InventoryManager.OpenInventory(adversaire);
    }


    public static void SupprimerJoueur(Player player){
        Game.JeuNumeroJoueur.remove(player);
        Game.JeuAdversaire.remove(player);
        Game.JeuTour.remove(player);
        Game.InventaireJoueur.remove(player);
        Game.JeuEnplacementPions.remove(player, 12);
        Game.JeuEnplacementPions.remove(player, 13);
        Game.JeuEnplacementPions.remove(player, 14);
        Game.JeuEnplacementPions.remove(player, 21);
        Game.JeuEnplacementPions.remove(player, 22);
        Game.JeuEnplacementPions.remove(player, 23);
        Game.JeuEnplacementPions.remove(player, 30);
        Game.JeuEnplacementPions.remove(player, 31);
        Game.JeuEnplacementPions.remove(player, 32);
    }
}
