package info.itsthesky.AcoiCore;

import de.leonhard.storage.Config;
import info.itsthesky.AcoiCore.core.ScoreBoard;
import info.itsthesky.AcoiCore.files.Configuration;
import info.itsthesky.AcoiCore.tools.TimeWatch;
import info.itsthesky.AcoiCore.tools.Utils;
import org.bukkit.plugin.PluginManager;

import java.io.File;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Manager {

    private final AcoiCore instance;
    public Manager(AcoiCore instance) {
        this.instance = instance;
    }

    public long registerCommands() {
        TimeWatch time = TimeWatch.start();

        return time.time(TimeUnit.MILLISECONDS);
    }

    public long registerEvents() {
        TimeWatch time = TimeWatch.start();
        PluginManager pm = instance.getServer().getPluginManager();

        pm.registerEvents(new ScoreBoard(), instance);

        return time.time(TimeUnit.MILLISECONDS);
    }

    public long registerFiles() {
        TimeWatch time = TimeWatch.start();

        Config config = new Config(new File(Utils.getPath() + "/config.yml"));
        Configuration.SCOREBOARD_TITLE = config.getOrSetDefault("Scoreboard.Title", Utils.colored("&3&l- &bAcoiNation &3&l-"));
        Configuration.SCOREBOARD_LINES = Arrays.asList(config.getOrSetDefault("Scoreboard.Lines", new String[] {
                "&1",
                "&2&l» &aJoueur: &6%player%",
                "&2&l» &aAutre valeurs ...",
                "&2&l» &aDemandez-moi (à Sky)",
                "&2&l» &aPour d'autre placeholder :)"
        }).clone());

        return time.time(TimeUnit.MILLISECONDS);
    }

}
