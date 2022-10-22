package fr.akinaru.morpion;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;


public class InventoryManager implements Listener{
    public Inventory inv;


    public static void showInventory(Player player) {

        Inventory inv = (Inventory) Bukkit.createInventory(null, 45,"§7Morpion");
        placeVitre(inv, player);
        placeTete(inv, player);
        ItemStack papier = new ItemStack(Material.GLOW_ITEM_FRAME);
        ItemMeta papierMeta = papier.getItemMeta();
        papierMeta.setDisplayName("§7Morpion");
        ArrayList<String> lore = new ArrayList<>();
        lore.add("§f ");
        if(Game.JeuTour.get(player).equals(player)){
            lore.add("§7Tour actuel: §9"+player.getName());
        }else{
            Player adversaire = (Player) Game.Jeu.get(player);
            lore.add("§7Tour actuel: §9"+adversaire.getName());
        }
        papierMeta.setLore(lore);
        papier.setItemMeta(papierMeta);
        inv.setItem(4, papier);
        player.openInventory((org.bukkit.inventory.Inventory) inv);

    }

    private static void placeVitre(Inventory inv, Player player) {
        Player adversaire = (Player) Game.Jeu.get(player);
        ItemStack vitre = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
        ItemStack vitreGrise = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemStack vitreVerte = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
        ItemMeta vitreMeta = vitre.getItemMeta();
        ItemMeta vitreVerteMeta = vitreVerte.getItemMeta();
        ItemMeta vitreGriseMeta = vitreVerte.getItemMeta();
        vitreMeta.setDisplayName("§f");
        vitreVerteMeta.setDisplayName("§7C'est au tour de ce joueur !");
        vitreGriseMeta.setDisplayName("§7C'est au tour de l'autre joueur !");
        vitre.setItemMeta(vitreMeta);
        vitreVerte.setItemMeta(vitreVerteMeta);
        vitreGrise.setItemMeta(vitreGriseMeta);

        ArrayList<Integer> list = new ArrayList<Integer>();
        inv.setItem(0,vitre);
        inv.setItem(1,vitre);
        inv.setItem(2,vitre);
        inv.setItem(3,vitre);
        inv.setItem(4,vitre);
        inv.setItem(5,vitre);
        inv.setItem(5,vitre);
        inv.setItem(6,vitre);
        inv.setItem(7,vitre);
        inv.setItem(8,vitre);
        inv.setItem(18,vitre);
        inv.setItem(25,vitre);
        inv.setItem(36,vitre);
        inv.setItem(37,vitre);
        inv.setItem(38,vitre);
        inv.setItem(39,vitre);
        inv.setItem(40,vitre);
        inv.setItem(41,vitre);
        inv.setItem(42,vitre);
        inv.setItem(43,vitre);
        inv.setItem(44,vitre);

        if(Game.JeuTour.get(player).equals(player)){
            //gauche
            inv.setItem(9,vitreVerte);
            inv.setItem(10,vitreVerte);
            inv.setItem(11,vitreVerte);
            inv.setItem(18,vitreVerte);
            inv.setItem(20,vitreVerte);
            inv.setItem(27,vitreVerte);
            inv.setItem(28,vitreVerte);
            inv.setItem(29,vitreVerte);
            //droite
            inv.setItem(15,vitreGrise);
            inv.setItem(16,vitreGrise);
            inv.setItem(17,vitreGrise);
            inv.setItem(24,vitreGrise);
            inv.setItem(26,vitreGrise);
            inv.setItem(33,vitreGrise);
            inv.setItem(34,vitreGrise);
            inv.setItem(35,vitreGrise);
        }else{
            //gauche
            inv.setItem(9,vitreGrise);
            inv.setItem(10,vitreGrise);
            inv.setItem(11,vitreGrise);
            inv.setItem(18,vitreGrise);
            inv.setItem(20,vitreGrise);
            inv.setItem(27,vitreGrise);
            inv.setItem(28,vitreGrise);
            inv.setItem(29,vitreGrise);
            //droite
            inv.setItem(15,vitreVerte);
            inv.setItem(16,vitreVerte);
            inv.setItem(17,vitreVerte);
            inv.setItem(24,vitreVerte);
            inv.setItem(26,vitreVerte);
            inv.setItem(33,vitreVerte);
            inv.setItem(34,vitreVerte);
            inv.setItem(35,vitreVerte);
        }


    }

    private static void placeTete(Inventory inv, Player player){
        ItemStack skull = new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (short) 3);
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
        skullMeta.setDisplayName("§9Toi §8(§7"+player.getName()+"§8)");
        skullMeta.setOwner(player.getName());
        ArrayList<String> lore = new ArrayList<String>();
        lore.add("§f ");
        skullMeta.setLore(lore);
        skull.setItemMeta(skullMeta);

        Player adversaire = (Player) Game.Jeu.get(player);
        ItemStack skull2 = new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (short) 3);
        SkullMeta skull2Meta = (SkullMeta) skull2.getItemMeta();
        skull2Meta.setDisplayName("§9"+adversaire.getName());
        skull2Meta.setOwner(adversaire.getName());
        ArrayList<String> lore2 = new ArrayList<String>();
        lore2.add("§f ");
        skull2Meta.setLore(lore2);
        skull2.setItemMeta(skull2Meta);

        inv.setItem(19,skull);
        inv.setItem(25,skull2);
    }
}
