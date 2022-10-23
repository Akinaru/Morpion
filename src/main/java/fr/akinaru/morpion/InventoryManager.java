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


    public static void OpenInventory(Player player) {
        if(!Game.InventaireJoueur.containsKey(player)){
            Inventory inv = Bukkit.createInventory(null, 45,"§7Morpion");
            CreateItems(player, inv);
            player.openInventory(inv);
            Game.InventaireJoueur.put(player, inv);
        }
    }

    public static void UpdateInventory(Player player){
        Inventory inv = Game.InventaireJoueur.get(player);
        CreateItems(player, inv);
    }

    private static void CreateItems(Player player, Inventory inv){
        placeVitre(inv, player);
        placeTete(inv, player);
        placePion(inv, player);
        ItemStack papier = new ItemStack(Material.GLOW_ITEM_FRAME);
        ItemMeta papierMeta = papier.getItemMeta();
        papierMeta.setDisplayName("§7Morpion");
        ArrayList<String> lore = new ArrayList<>();
        lore.add("§f ");
        if(Game.JeuTour.get(player).equals(player)){
            lore.add("§7Tour actuel: §9"+player.getName());
        }else{
            Player adversaire = (Player) Game.JeuAdversaire.get(player);
            lore.add("§7Tour actuel: §9"+adversaire.getName());
        }
        papierMeta.setLore(lore);
        papier.setItemMeta(papierMeta);
        inv.setItem(4, papier);
    }

    private static void placeVitre(Inventory inv, Player player) {
        Player adversaire = (Player) Game.JeuAdversaire.get(player);

        ItemStack vitre = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
        ItemStack vitreGrise = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemStack vitreGriseClair = new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE);
        ItemStack vitreVerte = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);

        ItemMeta vitreMeta = vitre.getItemMeta();
        ItemMeta vitreVerteMeta = vitreVerte.getItemMeta();
        ItemMeta vitreGriseMeta = vitreGrise.getItemMeta();
        ItemMeta vitreGriseClairMeta = vitreGriseClair.getItemMeta();
        vitreMeta.setDisplayName("§f");
        vitreVerteMeta.setDisplayName("§7C'est au tour de ce joueur !");
        vitreGriseMeta.setDisplayName("§7C'est au tour de l'autre joueur !");
        vitreGriseClairMeta.setDisplayName("§7Case Libre");
        vitre.setItemMeta(vitreMeta);
        vitreVerte.setItemMeta(vitreVerteMeta);
        vitreGrise.setItemMeta(vitreGriseMeta);
        vitreGriseClair.setItemMeta(vitreGriseClairMeta);


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

        inv.setItem(12,vitreGriseClair);
        inv.setItem(13,vitreGriseClair);
        inv.setItem(14,vitreGriseClair);
        inv.setItem(21,vitreGriseClair);
        inv.setItem(22,vitreGriseClair);
        inv.setItem(23,vitreGriseClair);
        inv.setItem(30,vitreGriseClair);
        inv.setItem(31,vitreGriseClair);
        inv.setItem(32,vitreGriseClair);

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

    private static void CreatePion(Inventory inv, Player player,Integer joueurNumero, Integer position){
        if(joueurNumero == 1){
            ItemStack item = new ItemStack(Material.ORANGE_DYE);
            ItemMeta itemMeta = item.getItemMeta();
            itemMeta.setDisplayName("§7Pion posé par §6"+Game.JeuEnplacementPions.get(player, position).getName());
            item.setItemMeta(itemMeta);
            inv.setItem(position, item);
        }else if(joueurNumero == 2){
            ItemStack item = new ItemStack(Material.GRAY_DYE);
            ItemMeta itemMeta = item.getItemMeta();
            itemMeta.setDisplayName("§7Pion posé par §6"+Game.JeuEnplacementPions.get(player, position).getName());
            item.setItemMeta(itemMeta);
            inv.setItem(position, item);
        }
    }

    private static void placePion(Inventory inv, Player player){
        if(Game.JeuNumeroJoueur.get(player) == 1){
            Player player1 = (Player) player;
            Player player2 = (Player) Game.JeuAdversaire.get(player);
            CheckPion(inv, player1,1);
            CheckPion(inv, player2,2);

        }else{
            Player player2 = (Player) player;
            Player player1 = (Player) Game.JeuAdversaire.get(player);
            CheckPion(inv, player1,1);
            CheckPion(inv, player2,2);
        }

    }

    private static void CheckPion(Inventory inv, Player player, Integer numero){
            if (Game.JeuEnplacementPions.contains(player, 12) && Game.JeuEnplacementPions.get(player, 12).equals(player)) CreatePion(inv, player, numero, 12);
            if (Game.JeuEnplacementPions.contains(player, 13) && Game.JeuEnplacementPions.get(player, 13).equals(player)) CreatePion(inv, player, numero, 13);
            if (Game.JeuEnplacementPions.contains(player, 14) && Game.JeuEnplacementPions.get(player, 14).equals(player)) CreatePion(inv, player, numero, 14);
            if (Game.JeuEnplacementPions.contains(player, 21) && Game.JeuEnplacementPions.get(player, 21).equals(player)) CreatePion(inv, player, numero, 21);
            if (Game.JeuEnplacementPions.contains(player, 22) && Game.JeuEnplacementPions.get(player, 22).equals(player)) CreatePion(inv, player, numero, 22);
            if (Game.JeuEnplacementPions.contains(player, 23) && Game.JeuEnplacementPions.get(player, 23).equals(player)) CreatePion(inv, player, numero, 23);
            if (Game.JeuEnplacementPions.contains(player, 30) && Game.JeuEnplacementPions.get(player, 30).equals(player)) CreatePion(inv, player, numero, 30);
            if (Game.JeuEnplacementPions.contains(player, 31) && Game.JeuEnplacementPions.get(player, 31).equals(player)) CreatePion(inv, player, numero, 31);
            if (Game.JeuEnplacementPions.contains(player, 32) && Game.JeuEnplacementPions.get(player, 32).equals(player)) CreatePion(inv, player, numero, 32);

    }

    private static void placeTete(Inventory inv, Player player){
        ItemStack skull = new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (short) 3);
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
        skullMeta.setDisplayName("§9Toi §8(§7"+player.getName()+"§8)");
        skullMeta.setOwner(player.getName());
        ArrayList<String> lore = new ArrayList<String>();
        lore.add("§f ");
        if(Game.JeuNumeroJoueur.get(player) == 1){
            lore.add("§7Couleur de pion: §6Orange");
        }else{
            lore.add("§7Couleur de pion: Gris");
        }
        skullMeta.setLore(lore);
        skull.setItemMeta(skullMeta);

        Player adversaire = (Player) Game.JeuAdversaire.get(player);
        ItemStack skull2 = new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (short) 3);
        SkullMeta skull2Meta = (SkullMeta) skull2.getItemMeta();
        skull2Meta.setDisplayName("§9"+adversaire.getName());
        skull2Meta.setOwner(adversaire.getName());
        ArrayList<String> lore2 = new ArrayList<String>();
        lore2.add("§f ");
        if(Game.JeuNumeroJoueur.get(adversaire) == 1){
            lore2.add("§7Couleur de pion: §6Orange");
        }else{
            lore2.add("§7Couleur de pion: Gris");
        }
        skull2Meta.setLore(lore2);
        skull2.setItemMeta(skull2Meta);

        inv.setItem(19,skull);
        inv.setItem(25,skull2);
    }
}
