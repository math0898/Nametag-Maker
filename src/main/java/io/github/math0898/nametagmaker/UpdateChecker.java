package io.github.math0898.nametagmaker;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

/**
 * This class defines the UpdateChecker for Nametag Maker. Used to check for updates onEnable and when a dev logs in.
 *
 * @author Sugaku
 */
public class UpdateChecker implements Listener {

    /**
     * The updated version string.
     */
    public static String latest = null;

    /**
     * Checks if an update is needed using the version string in main.
     *
     * @return true if and only if an update is available.
     */
    public static boolean canUpdate () {
        String c = main.version;
        String l = latest;
        if (l == null) return false;
        l = l.replace("v", "").replace(".", "");
        c = c.replace("v", "").replace(".", "");
        return (Integer.parseInt(l) > Integer.parseInt(c)); //Breaks with multiple digits not in the first column
    }

    /**
     * When a player joins determines if they should be bothered with a message that an update is available.
     *
     * @param event The player join event.
     */
    @EventHandler
    public static void onJoin (PlayerJoinEvent event) {
        Bukkit.getScheduler().runTaskAsynchronously(main.plugin, () -> joinAsync(event.getPlayer()));
    }

    /**
     * Handles the join event asynchronously.
     *
     * @param p The player who just joined.
     */
    private static void joinAsync (Player p) {
        if (p.hasPermission("nametagmaker.update")) {
            try {
                InputStream stream = new URL("https://api.spigotmc.org/legacy/update.php?resource=95036").openStream();
                Scanner s = new Scanner(stream);
                if (s.hasNext()) latest = s.next();
            } catch (IOException ignored) {
                main.console("Checking for updates failed!", ChatColor.RED);
            }
            if (canUpdate()) {
                String e;
                if (Config.enabled) e = ChatColor.GREEN + "Enabled";
                else e = ChatColor.RED + "Disabled";
                p.sendMessage(Lang.prefix + ChatColor.GOLD + "Nametag " + ChatColor.AQUA + "Maker " + ChatColor.GRAY + main.version + " - " + e);
                p.sendMessage(Lang.prefix + "Update to " + ChatColor.GREEN + latest + ChatColor.GRAY + " available!");
                p.sendMessage(Lang.prefix + "https://spigotmc.org/resources/nametag-maker.95036/");
            }
        }
    }
}
