package fr.akinaru.morpion;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;


public class InventoryManager implements Listener{
    public Inventory inv;


    public static void showInventory(Player player) {

        Inventory inv = (Inventory) Bukkit.createInventory(null, 45,"§7Morpion");
        placeVitre(inv);
        ItemStack papier = new ItemStack(Material.GLOW_ITEM_FRAME);
        ItemMeta papierMeta = papier.getItemMeta();
        papierMeta.setDisplayName("§7Morpion");
        ArrayList<String> lore = new ArrayList<>();
        lore.add("§f ");
        lore.add("§8- §7Tu es en duel contre §5"+Game.Jeu.get(player).getName());
        papierMeta.setLore(lore);
        papier.setItemMeta(papierMeta);
        inv.setItem(4, papier);
        player.openInventory((org.bukkit.inventory.Inventory) inv);

    }

    private static void placeVitre(Inventory inv) {
        ItemStack vitre = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
        ItemMeta vitreMeta = vitre.getItemMeta();
        vitreMeta.setDisplayName("§f");
        vitre.setItemMeta(vitreMeta);
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i =0; i <= 11; i++) inv.setItem(i, vitre);
        for(int i =15; i <= 20; i++) inv.setItem(i, vitre);
        for(int i =24; i <= 29; i++) inv.setItem(i, vitre);
        for(int i =33; i <= 44; i++) inv.setItem(i, vitre);

    }
}
