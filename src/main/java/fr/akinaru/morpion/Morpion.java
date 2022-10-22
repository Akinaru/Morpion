package fr.akinaru.morpion;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Morpion extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Plugin de Morpion activé !");
        getCommand("morpion").setExecutor(new Command());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
