package fr.akinaru.morpion;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;

public class Listeners implements Listener {

    static Morpion plugin;

    @EventHandler
    public static void Disconnect(PlayerQuitEvent e) {
        Player player = (Player) e.getPlayer();
        if (Game.LastAsker.containsKey(player)) {
            Game.LastAsker.remove(player);
        }
    }

    @EventHandler
    public static void InventoryClick(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        Inventory inv = e.getInventory();
        String nom = e.getView().getTitle();

        if(nom.contains("§7Morpion")){

            e.setCancelled(true);
        }
    }

    @EventHandler
    public static void InventoryClose(InventoryCloseEvent e){
        Player player = (Player) e.getPlayer();
        Inventory inv = e.getInventory();
        String nom = e.getView().getTitle();
        if(nom.contains("§7Morpion")){
            //
        }
    }

}
