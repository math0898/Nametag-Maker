package io.github.math0898.nametagmaker;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
    public static String version = "v0.1.4-alpha";

    /**
     * This method sends a message to the console.
     *
     * @param message The message to send to the console.
     * @param color The main color of the message being sent.
     */
    public static void console(String message, ChatColor color) { Bukkit.getConsoleSender().sendMessage(prefix + color + message); }

    /**
     * Handles the loading of config and lang, registering command managers, and event listeners.
     */
    @Override
    @SuppressWarnings("all")
    public void onEnable() {
        // Configuration loading
        console("Loading configuration.", ChatColor.GRAY);
        if (Config.init()) console("Configuration loaded.", ChatColor.GREEN);
        else console("Failed to load configuration!", ChatColor.RED);
        // Language loading
        console("Loading language file.", ChatColor.GRAY);
        if (Lang.init()) console("Language loaded.", ChatColor.GREEN);
        else console("Failed to load language!", ChatColor.RED);
        // Initializing Commands
        console("Setting up commands.", ChatColor.GRAY);
        try {
            this.getCommand("nametag").setExecutor(new MainCommand()); //May produce null pointer
            this.getCommand("nametag").setTabCompleter(MainCommand.autocomplete); //May produce null pointer
            console("Command setup successful!", ChatColor.GREEN);
        } catch (NullPointerException exception) {
            console("Could not setup command.", ChatColor.RED);
            console(exception.toString(), ChatColor.RED);
        }
        // Register events
        console("Registering event listeners.", ChatColor.GRAY);
        Bukkit.getPluginManager().registerEvents(new NametagApplier(), this);
        console("Events registered.", ChatColor.GREEN);
        // Reading Tags.yml and setting up teams
        console("Initializing teams.", ChatColor.GRAY);
        NametagApplier.clean();
        NametagApplier.init();
        console("Teams initialized!", ChatColor.GREEN);
        // Console the result of the load.
        if (Config.enabled) console("Loading successful. Plugin enabled.", ChatColor.GREEN);
        else console("Plugin is disabled. If this is a mistake please check the config and report.", ChatColor.RED);
    }

    /**
     * Takes care of anything that needs to be done on shutdown down. There isn't anything that generally goes here and
     * is mostly plugin specific.
     */
    @Override
    public void onDisable() {
        console("Removing teams.", ChatColor.GRAY);
        NametagApplier.clean();
        console("Teams removed.", ChatColor.GREEN);
        console("Tear down successful!", ChatColor.GREEN);
    }
}
