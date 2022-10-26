package fr.akinaru.morpion;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;

public class Listeners implements Listener {


    @EventHandler
    public static void Disconnect(PlayerQuitEvent e) {
        Player player = (Player) e.getPlayer();
        if (Game.LastAsker.containsKey(player)) {
            Game.LastAsker.remove(player);

        }if(Game.InventaireJoueur.containsKey(player)){
            Game.InventaireJoueur.remove(player);
        }
        if(Game.JeuAdversaire.containsKey(player)){
            Player adversaire = (Player) Game.JeuAdversaire.get(player);
            Game.SupprimerJoueur(player);
            Game.SupprimerJoueur(adversaire);
            adversaire.closeInventory();
            adversaire.sendMessage("§7Tu as gagné par forfait !");
        }
    }

    @EventHandler
    public static void InventoryClick(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        Inventory inv = e.getInventory();
        String nom = e.getView().getTitle();

        if(nom.contains("§7Morpion")){

            if(Game.JeuTour.get(player) != null && Game.JeuTour.get(player).equals(player)){
                if(e.getCurrentItem() != null && e.getCurrentItem().getType().equals(Material.LIGHT_GRAY_STAINED_GLASS_PANE)){
                    if(e.getCurrentItem().getItemMeta().getDisplayName().contains("§7Case Libre")){
                        Game.PoserPion(player, e);

                    }
                }
            }
            e.setCancelled(true);
        }
    }

    @EventHandler
    public static void InventoryClose(InventoryCloseEvent e){
        Player player = (Player) e.getPlayer();
        Inventory inv = e.getInventory();
        String name = e.getView().getTitle();
        if(name.contains("§7Morpion")){
            //Player adversaire = Game.JeuAdversaire.get(player);
            //Game.stopGame(player);
            //Game.stopGame(adversaire);
            //player.sendMessage("§7Fin de la partie, vous avez fermé le menu !");
            //adversaire.sendMessage("§7Fin de la partie, §6"+player.getName()+" §7a fermé le menu !");
            //Game.InventaireJoueur.remove(player);
            //Bukkit.getScheduler().runTaskLater(Morpion.getPlugin(), () -> {
                //InventoryManager.OpenInventory(player);
            //}, 20L/10);
        }
    }

}
