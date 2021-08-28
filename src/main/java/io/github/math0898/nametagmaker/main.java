package io.github.math0898.nametagmaker;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * The main class of the plugin. From here everything is enabled and disabled on server startup and shutdown. This class
 * also serves as a good location to add some methods for logging to console.
 *
 * @author Sugaku
 */
public final class main extends JavaPlugin {

    /**
     * The prefix for messages sent to console for this plugin.
     */
    public static String prefix = ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "Nametag Maker" + ChatColor.DARK_GRAY + "] ";

    /**
     * String representation of the version of the plugin.
     */
    public static String version = "v2.1.2";

    /**
     * A pointer to the java plugin.
     */
    public static JavaPlugin plugin = null;

    /**
     * This method sends a message to the console.
     *
     * @param message The message to send to the console.
     * @param color The main color of the message being sent.
     */
    public static void console(String message, ChatColor color) { Bukkit.getConsoleSender().sendMessage(prefix + color + message); }

    /**
     * Sends an ASCI art to the console... very important to the functionality of the plugin.
     */
    public static void asciArt () {
        CommandSender console = Bukkit.getConsoleSender();
        console.sendMessage("");
        console.sendMessage(ChatColor.GOLD + "  |\\ |"  + " ---" + " |\\  /|    " + prefix.replace("[", "").replace("]", "").replace("6","b") + ChatColor.DARK_GREEN + version);
        console.sendMessage(ChatColor.GOLD + "  | \\|"  + "  | " + " | \\/ |    " + ChatColor.DARK_GRAY + "Server Version - " + Bukkit.getVersion());
        console.sendMessage("");
    }

    /**
     * Handles the loading of config and lang, registering command managers, and event listeners.
     */
    @Override
    @SuppressWarnings("all")
    public void onEnable() {
        long start = System.currentTimeMillis();
        asciArt();
        plugin = this;
        // Configuration loading
        console("Loading configuration...", ChatColor.GRAY);
        if (!Config.init()) console("Failed to load configuration!", ChatColor.RED);
        // Language loading
        if (!Lang.init()) console("Failed to load language!", ChatColor.RED);
        // Initializing Commands
        try {
            this.getCommand("nametag").setExecutor(new MainCommand()); //May produce null pointer
            this.getCommand("nametag").setTabCompleter(MainCommand.autocomplete); //May produce null pointer
        } catch (NullPointerException exception) {
            console("Could not setup commands.", ChatColor.RED);
            console(exception.toString(), ChatColor.RED);
        }
        // Register events
        Bukkit.getPluginManager().registerEvents(new NametagApplier(), this);
        Bukkit.getPluginManager().registerEvents(new UpdateChecker(), this);
        // Reading Tags.yml and setting up teams
        console("Initializing teams...", ChatColor.GRAY);
        NametagApplier.clean();
        NametagApplier.init();
        // Console the result of the load.
        if (Config.enabled) console("Loading successful. Plugin enabled." + ChatColor.DARK_GRAY + " Took: " + (System.currentTimeMillis() - start) + "ms", ChatColor.GREEN);
        else console("Plugin is disabled. If this is a mistake please check the config and report." + ChatColor.DARK_GRAY + " Took: " + (System.currentTimeMillis() - start) + "ms", ChatColor.RED);
    }

    /**
     * Takes care of anything that needs to be done on shutdown down. There isn't anything that generally goes here and
     * is mostly plugin specific.
     */
    @Override
    public void onDisable() {
        console("Removing teams.", ChatColor.GRAY);
        NametagApplier.clean();
        console("Tear down successful!", ChatColor.GREEN);
    }
}
