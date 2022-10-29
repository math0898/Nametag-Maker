package io.github.math0898.nametagmaker;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.github.math0898.nametagmaker.NametagMaker.plugin;

/**
 * This class handles the configuration for this plugin. It's used to read what admins have changed and write the
 * default configurations.
 *
 * @author Sugaku
 */
public record Config () {

    /**
     * A boolean which states whether the plugin is enabled.
     */
    public static boolean enabled;

    /**
     * The prefix that will be used for teams on the scoreboard.
     */
    public static String prefix;

    /**
     * Initializes the configuration for the plugin. It checks if the file exists and creates it if it needs to be. Then
     * it moves onto reading the configuration.
     *
     * @return Whether initialization was a success or not.
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static boolean init () {
        try {
            File container = new File("./plugins/NametagMaker/");
            if (!Files.exists(Paths.get(container.getPath()))) container.mkdir();
            File config = new File("./plugins/NametagMaker/config.yml");
            if (!Files.exists(Paths.get(config.getPath()))) plugin.saveConfig();
            return read();
        }
        catch (Exception exception){
            NametagMaker.console("Could not create directories.",  ChatColor.RED);
            NametagMaker.console(exception.toString(), ChatColor.RED);
            return false;
        }
    }

    /**
     * Reads the configuration for the plugin. Hosted at plugins/'PluginName'/config.yml
     *
     * @return Whether reading the config was a success or not.
     */
    public static boolean read () {
        FileConfiguration config = new YamlConfiguration();
        try {
            config.load("./plugins/NametagMaker/config.yml");
        } catch (Exception exception) {
            NametagMaker.console("Could not read file.",  ChatColor.RED);
            NametagMaker.console(exception.toString(), ChatColor.RED);
            return false;
        }
        enabled = config.getBoolean("general.enabled", false);
        prefix = config.getString("general.prefix", "nt-");
        return true;
    }
}
