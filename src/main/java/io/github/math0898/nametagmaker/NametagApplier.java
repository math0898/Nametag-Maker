package io.github.math0898.nametagmaker;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

/**
 * This class handles the application and continued application of nametags to players.
 *
 * @author Sugaku
 */
public class NametagApplier implements Listener {

    /**
     * When a player joins the server... apply the desired nametag to them.
     *
     * @param event The PlayerJoin event to be handled
     */
    @EventHandler
    public static void onJoin (PlayerJoinEvent event) {
        if (!Config.enabled) return;
        update(event.getPlayer());
    }

    /**
     * Initializes teams based on what is read from Tags.yml
     */
    public static void init () {
        if (!Config.enabled) return;
        if (!Tags.init()) return;
        assert Bukkit.getScoreboardManager() != null;
        Scoreboard s = Bukkit.getScoreboardManager().getMainScoreboard();
        for (TagGroup g: Tags.groups) {
            String name = "nt-" + g.name;
            s.registerNewTeam(name);
            Team t = s.getTeam(name);
            assert t != null;
            t.setColor(g.color);
            t.setPrefix(g.prefix);
            t.setSuffix(g.suffix);
            if (!g.visible) t.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.NEVER);
        }
    }

    /**
     * Refreshes the nametags of all players currently online.
     */
    public static void refresh () {
        if (!Config.enabled) return;
        for (Player p: Bukkit.getOnlinePlayers()) update(p);
    }

    /**
     * Updates the team for the given Player p.
     *
     * @param p The player to be updated.
     */
    @SuppressWarnings("all")
    public static void update (Player p) {
        if (!Config.enabled) return;
        for (TagGroup g: Tags.groups) {  // O(n)
            Team current = Bukkit.getScoreboardManager().getMainScoreboard().getEntryTeam(p.getName());
            if (current != null) {
                TagGroup currentTag = Tags.findTeam(current.getName());
                if (currentTag != null) if (currentTag.weight >= g.weight) continue;
            }
            if (g.permission != null && g.permission != "null") if (p.hasPermission(g.permission)) {
                if (current != null) current.removeEntry(p.getName());
                Bukkit.getScoreboardManager().getMainScoreboard().getTeam("nt-" + g.name).addEntry(p.getName());
            }
            for (String n: g.players) { // O(n^2)!
                if (n.equalsIgnoreCase(p.getName())) {
                    if (current != null) current.removeEntry(p.getName());
                    Bukkit.getScoreboardManager().getMainScoreboard().getTeam("nt-" + g.name).addEntry(p.getName());
                    break;
                }
            }
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
        for (Team t: Bukkit.getScoreboardManager().getMainScoreboard().getTeams()) if (t.getName().contains("nt-")) t.unregister();
    }
}
