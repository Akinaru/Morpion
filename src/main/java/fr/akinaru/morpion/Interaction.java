package fr.akinaru.morpion;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Interaction implements Listener {

    public static void sendRequest(Player envoyeur, Player destinataire){
        envoyeur.sendMessage("§7Tu as envoyé une demande de duel à §9"+destinataire.getName() + "§7!");
        destinataire.sendMessage("§7Tu as reçu un duel de §3Morpion §7par §6"+envoyeur.getName() + "§7. §5/morpion accept §7pour accepter.");
        if(Game.LastAsker.containsKey(destinataire)){
            Game.LastAsker.remove(destinataire);
        }
        Game.LastAsker.put(destinataire, envoyeur);
    }

    public static void acceptRequest(Player player){
        Player adversaire = (Player) Game.LastAsker.get(player);
        adversaire.sendMessage("§5"+player.getName()+" §7a accepté le duel de Morpion !");
        Game.Jeu.put(player, adversaire);
        Game.Jeu.put(adversaire, player);
        Game.JeuTour.put(player, player);
        Game.JeuTour.put(adversaire, player);
        Game.LastAsker.remove(player);
        Game.LastAsker.remove(adversaire);
        Game.startGame(player, adversaire);

    }

}
