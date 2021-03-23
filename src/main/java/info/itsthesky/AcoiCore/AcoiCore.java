package info.itsthesky.AcoiCore;

import info.itsthesky.AcoiCore.tools.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class AcoiCore extends JavaPlugin {

    private static AcoiCore INSTANCE;
    public static AcoiCore getInstance() {
        return INSTANCE;
    }

    @Override
    public void onEnable() {
        INSTANCE = this;
        ConsoleCommandSender console = Bukkit.getConsoleSender();
        Manager manager = new Manager(this);

        console.sendMessage(Utils.colored("&7---------------------"));
        console.sendMessage(Utils.colored("&6Chargement d'&eAcoiCore &6version &e" + getDescription().getVersion() + " &6..."));
        console.sendMessage(Utils.colored(""));
        console.sendMessage(Utils.colored("&9Informations:"));
        console.sendMessage(Utils.colored("  &8- &3Développeur: &bItsTheSky#1234"));
        console.sendMessage(Utils.colored("  &8- &3Version: &b" + getDescription().getVersion()));
        console.sendMessage(Utils.colored("  &8- &3Type: &bCore"));
        console.sendMessage(Utils.colored(""));
        console.sendMessage(Utils.colored("&5[1/3] &6Chargement des fichiers de configurations ..."));
        console.sendMessage(Utils.colored("&5[1/3] &aTerminé! Temps: "+manager.registerFiles()+" seconde(s)."));
        console.sendMessage(Utils.colored("&5[2/3] &6Chargement des listeners ..."));
        console.sendMessage(Utils.colored("&5[2/3] &aTerminé! Temps: "+manager.registerEvents()+" seconde(s)."));
        console.sendMessage(Utils.colored("&5[3/3] &6Chargement des fichiers de configurations ..."));
        console.sendMessage(Utils.colored("&5[3/3] &aTerminé! Temps: "+manager.registerFiles()+" seconde(s)."));


    }
}
