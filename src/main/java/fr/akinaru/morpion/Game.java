package fr.akinaru.morpion;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.HashMap;

public class Game implements Listener {
    public static HashMap<Player, Player> Jeu = new HashMap<Player, Player>();
    public static HashMap<Player, Player> LastAsker = new HashMap<Player, Player>();
}
