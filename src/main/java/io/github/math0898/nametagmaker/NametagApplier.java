package io.github.math0898.nametagmaker;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Objects;

/**
 * This class handles the application and continued application of nametags to players.
 *
 * @author Sugaku
 */
public class NametagApplier implements Listener {

    //TODO: Implement enabled.

    /**
     * When a player joins the server... apply the desired nametag to them.
     *
     * @param event The PlayerJoin event to be handled
     */
    @EventHandler
    public static void onJoin (PlayerJoinEvent event) {
        update(event.getPlayer());
    }

    /**
     * Initializes teams based on what is read from Tags.yml
     */
    public static void init () {
        //TODO: Tags.yml, reading, creating, and using.
        Objects.requireNonNull(Bukkit.getScoreboardManager()).getMainScoreboard().registerNewTeam("nametag-" + "default");
        Objects.requireNonNull(Bukkit.getScoreboardManager().getMainScoreboard().getTeam("nametag-" + "default")).setColor(ChatColor.GREEN);
    }

    /**
     * Refreshes the nametags of all players currently online.
     */
    public static void refresh () {
        for (Player p: Bukkit.getOnlinePlayers()) update(p);
    }

    /**
     * Updates the team for the given Player p.
     *
     * @param p The player to be updated.
     */
    @SuppressWarnings("all")
    public static void update (Player p) {
        //TODO: Add logic for determining other teams.
        try {
            Objects.requireNonNull(Bukkit.getScoreboardManager().getMainScoreboard().getTeam("nametag-default")).addEntry(p.getDisplayName()); //May produce null pointer
        } catch (NullPointerException ignored) {
            //TODO
        }
    }

    /**
     * Removes all teams built by NametagApplier.java to make sure things are squeaky clean when we leave.
     */
    public static void clean () {
        if (Bukkit.getScoreboardManager() == null ) {
            main.console("Scoreboard manager is not defined!", ChatColor.RED);
            return;
        }
        for (Team t: Bukkit.getScoreboardManager().getMainScoreboard().getTeams()) if (t.getName().contains("nametag-")) t.unregister();
    }
}
