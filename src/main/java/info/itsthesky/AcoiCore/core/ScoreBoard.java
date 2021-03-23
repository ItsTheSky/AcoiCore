package info.itsthesky.AcoiCore.core;

import info.itsthesky.AcoiCore.AcoiCore;
import info.itsthesky.AcoiCore.files.Configuration;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class ScoreBoard implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        new BukkitRunnable() {
            @Override
            public void run() {

                String name = Configuration.SCOREBOARD_TITLE;

                ScoreboardManager score = Bukkit.getScoreboardManager();
                Scoreboard board = score.getNewScoreboard();
                Objective sb = board.registerNewObjective("sidebar", "dummy", name);
                sb.setDisplaySlot(DisplaySlot.SIDEBAR);
                sb.setDisplayName(name);

                int scoreID = 100;
                for (String s : Configuration.SCOREBOARD_LINES) {

                    sb.getScore(
                            s.replace("%player%", e.getPlayer().getName())
                    ).setScore(scoreID);
                    scoreID--;
                }
                player.setScoreboard(board);

            }}.runTaskTimer(AcoiCore.getInstance(), 10, 0);
    }

}
