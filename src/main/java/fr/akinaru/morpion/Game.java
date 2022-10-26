package fr.akinaru.morpion;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.bukkit.Bukkit;
import org.bukkit.Material;
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
    public static HashMap<Player, Boolean> Finish = new HashMap<Player, Boolean>();
    public static HashMap<Player, Inventory> InventaireJoueur = new HashMap<Player, Inventory>();
    public static Table<Player, Integer, Player> JeuEnplacementPions = HashBasedTable.create();


    public static void stopGame(Player player){
        SupprimerJoueur(player);
    }

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


    public static boolean PionPlacedBy(Player player, Integer position){
        if(Game.JeuEnplacementPions.contains(player, position) && Game.JeuEnplacementPions.get(player, position).equals(player)) return true;
        else return false;
    }

    private static void Win(Player player){
        if(Game.Finish.get(player) == null) {
            Player adversaire = Game.JeuAdversaire.get(player);
            player.sendMessage("§7Tu as gagné !");
            adversaire.sendMessage("§6" + player.getName() + " §7a gagné la partie !");
            Game.Finish.put(player, true);
            Game.Finish.put(adversaire, true);
            Bukkit.getScheduler().runTaskLater(Morpion.getPlugin(), () -> {
                stopGame(player);
                stopGame(adversaire);
                player.closeInventory();
                adversaire.closeInventory();
            }, 20L * 3);
        }
    }

    public static boolean CheckWin(Inventory inv, Player player) {
        Player adversaire = Game.JeuAdversaire.get(player);
        if (PionPlacedBy(player, 12) && PionPlacedBy(player, 13) && PionPlacedBy(player, 14))
            return true; // en haut horizontale
        if (PionPlacedBy(player, 21) && PionPlacedBy(player, 22) && PionPlacedBy(player, 23))
            return true; //au millieu horizontale
        if (PionPlacedBy(player, 30) && PionPlacedBy(player, 31) && PionPlacedBy(player, 32))
            return true; // en bas horizontale

        if (PionPlacedBy(player, 12) && PionPlacedBy(player, 21) && PionPlacedBy(player, 30))
            return true; // a gauche verticale
        if (PionPlacedBy(player, 13) && PionPlacedBy(player, 22) && PionPlacedBy(player, 32))
            return true; //au millieu verticale
        if (PionPlacedBy(player, 14) && PionPlacedBy(player, 23) && PionPlacedBy(player, 32))
            return true; // a droite verticale

        if (PionPlacedBy(player, 12) && PionPlacedBy(player, 22) && PionPlacedBy(player, 32))
            return true; // diagonale gauche vers droite
        if (PionPlacedBy(player, 14) && PionPlacedBy(player, 22) && PionPlacedBy(player, 30))
            return true; // diagonale droite vers gauche

        if(!(inv.getItem(12).getType().equals(Material.LIGHT_GRAY_STAINED_GLASS_PANE) )&&
             !(inv.getItem(13).getType().equals(Material.LIGHT_GRAY_STAINED_GLASS_PANE)) &&
             !(inv.getItem(14).getType().equals(Material.LIGHT_GRAY_STAINED_GLASS_PANE)) &&
             !(inv.getItem(21).getType().equals(Material.LIGHT_GRAY_STAINED_GLASS_PANE)) &&
             !(inv.getItem(22).getType().equals(Material.LIGHT_GRAY_STAINED_GLASS_PANE)) &&
             !(inv.getItem(23).getType().equals(Material.LIGHT_GRAY_STAINED_GLASS_PANE)) &&
             !(inv.getItem(30).getType().equals(Material.LIGHT_GRAY_STAINED_GLASS_PANE)) &&
             !(inv.getItem(31).getType().equals(Material.LIGHT_GRAY_STAINED_GLASS_PANE)) &&
             !(inv.getItem(32).getType().equals(Material.LIGHT_GRAY_STAINED_GLASS_PANE)))
        {
            if(Game.Finish.get(player) == null) {
                player.sendMessage("§7Partie finie, égalité !");
                adversaire.sendMessage("§7Partie finie, égalité !");
                Game.Finish.put(player, true);
                Game.Finish.put(adversaire, true);
                Bukkit.getScheduler().runTaskLater(Morpion.getPlugin(), () -> {
                    stopGame(player);
                    stopGame(adversaire);
                    player.closeInventory();
                    adversaire.closeInventory();
                }, 20L * 3);
                return true;
            }
            return false;
        }
        else return false;
    }

    public static void CheckPion(Inventory inv, Player player, Integer numero){
        if (Game.JeuEnplacementPions.contains(player, 12) && Game.JeuEnplacementPions.get(player, 12).equals(player)) InventoryManager.CreatePion(inv, player, numero, 12);
        if (Game.JeuEnplacementPions.contains(player, 13) && Game.JeuEnplacementPions.get(player, 13).equals(player)) InventoryManager.CreatePion(inv, player, numero, 13);
        if (Game.JeuEnplacementPions.contains(player, 14) && Game.JeuEnplacementPions.get(player, 14).equals(player)) InventoryManager.CreatePion(inv, player, numero, 14);
        if (Game.JeuEnplacementPions.contains(player, 21) && Game.JeuEnplacementPions.get(player, 21).equals(player)) InventoryManager.CreatePion(inv, player, numero, 21);
        if (Game.JeuEnplacementPions.contains(player, 22) && Game.JeuEnplacementPions.get(player, 22).equals(player)) InventoryManager.CreatePion(inv, player, numero, 22);
        if (Game.JeuEnplacementPions.contains(player, 23) && Game.JeuEnplacementPions.get(player, 23).equals(player)) InventoryManager.CreatePion(inv, player, numero, 23);
        if (Game.JeuEnplacementPions.contains(player, 30) && Game.JeuEnplacementPions.get(player, 30).equals(player)) InventoryManager.CreatePion(inv, player, numero, 30);
        if (Game.JeuEnplacementPions.contains(player, 31) && Game.JeuEnplacementPions.get(player, 31).equals(player)) InventoryManager.CreatePion(inv, player, numero, 31);
        if (Game.JeuEnplacementPions.contains(player, 32) && Game.JeuEnplacementPions.get(player, 32).equals(player)) InventoryManager.CreatePion(inv, player, numero, 32);
        if(Game.CheckWin(inv, player)){
            if(Game.Finish.get(player) == null){
                Win(player);

            }
        }
        if(Game.CheckWin(inv, Game.JeuAdversaire.get(player))){
            if(Game.Finish.get(Game.JeuAdversaire.get(player)) == null){
                Win(Game.JeuAdversaire.get(player));

            }
        }
    }

    public static void changeTour(Player player){
        Player adversaire = JeuAdversaire.get(player);
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
        Game.changeTour(player);
        Game.InventaireJoueur.remove(player);
        Game.InventaireJoueur.remove(adversaire);
        if(Game.JeuAdversaire.get(player) != null){
            InventoryManager.OpenInventory(player);
            InventoryManager.OpenInventory(adversaire);
        }

    }


    public static void SupprimerJoueur(Player player){
        Game.JeuNumeroJoueur.remove(player);
        Game.JeuAdversaire.remove(player);
        Game.JeuTour.remove(player);
        Game.Finish.remove(player);
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
