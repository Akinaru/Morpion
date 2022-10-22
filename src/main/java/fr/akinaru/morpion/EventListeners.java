package fr.akinaru.morpion;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class EventListeners implements Listener {

    @EventHandler
    public static void Disconnect(PlayerQuitEvent e) {
        Player player = (Player) e.getPlayer();
        if (Game.LastAsker.containsKey(player)) {
            Game.LastAsker.remove(player);
        }
    }

}
