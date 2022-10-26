package fr.akinaru.morpion;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Morpion extends JavaPlugin {

    private static Morpion plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        System.out.println("Plugin de Morpion activé !");
        getCommand("morpion").setExecutor(new Command());
        this.getCommand("morpion").setTabCompleter(new CommandTabCompletion());
        this.getServer().getPluginManager().registerEvents((Listener)new Listeners(), (Plugin)this);
    }

    @Override
    public void onDisable() {
        if(!Game.JeuAdversaire.isEmpty()) {
            for (Player players : Bukkit.getOnlinePlayers()) {
                if (Game.JeuAdversaire.containsKey(players)) {
                    players.sendMessage("§cLa partie a été interrompue.");
                    Game.stopGame(players);
                    players.closeInventory();
                }
            }
        }
    }

    public static Morpion getPlugin(){
        return plugin;
    }
}
