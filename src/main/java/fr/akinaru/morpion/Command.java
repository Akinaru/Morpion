package fr.akinaru.morpion;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        Player player = (Player) sender;
        if(args.length == 0){
            player.sendMessage("§cTu dois mettre un pseudo !");
            return false;
        }
        Player destinataire = (Player) Bukkit.getServer().getPlayer(args[0]);
        if(destinataire == null && !args[0].equals("accept")){
            player.sendMessage("§cLe joueur précisé n'est pas connecté !");
            return false;
        }
        if(destinataire == player){
            player.sendMessage("§cTu ne peux pas jouer avec toi même !");
            return false;
        }
        if(args[0].equals("accept")){
            if(Game.LastAsker.containsKey(player)){
                Interaction.acceptRequest(player);
                return false;
            }else{
                player.sendMessage("§cTu n'as personne avec qui jouer !");
                return false;
            }
        }
        if(Game.JeuAdversaire.containsKey(destinataire)){
            player.sendMessage("§cCe joueur est déjà en partie !");
            return false;
        }
        if(Game.LastAsker.get(player) == destinataire) {
            Interaction.acceptRequest(player);

        }else{
            Interaction.sendRequest(player, destinataire);

        }
        return false;
    }
}
