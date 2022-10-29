package io.github.math0898.nametagmaker.lang;

import io.github.math0898.nametagmaker.Config;
import io.github.math0898.nametagmaker.NametagMaker;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.Scanner;

import static io.github.math0898.nametagmaker.NametagMaker.plugin;

/**
 * This class handles all the messages which are sent to players and admins. This is placed here for easy access for
 * translations and configuring the plugin to match server branding.
 *
 * @author Sugaku
 */
public record Lang () {

    /**
     * The main prefix used for any messages sent during runtime.
     */
    public static String prefix =  "";

    /**
     * The message sent when attempting to disable the plugin and its already disabled.
     */
    public static String alreadyDisabled = "Already disabled. Use " + ChatColor.GREEN + "/nametag enable" + ChatColor.GRAY + " to enable.";

    /**
     * The message sent when disabling the plugins is successful.
     */
    public static String disable = ChatColor.RED + "Disabled. " + ChatColor.GRAY + "This will be overridden by config.yml on reload.";

    /**
     * The message sent on how to re-enable the plugin.
     */
    public static String reEnable = "Use " + ChatColor.GREEN + "/nametag enable" + ChatColor.GRAY + " to re-enable without a reload.";

    /**
     * The message sent on how to re-disable the plugin.
     */
    public static String reDisable = "Use " + ChatColor.GREEN + "/nametag disable" + ChatColor.GRAY + " to disable without a reload.";

    /**
     * The message sent when a tag name is not specified.
     */
    public static String noTag = ChatColor.RED + "Please specify a tag name.";

    /**
     * The message sent when a tag is not found.
     */
    public static String tagNotFound = ChatColor.RED + "Could not find a tag by that name.";

    /**
     * The message sent to console when the plugin is disabled.
     */
    public static String disabled = ChatColor.YELLOW + " has disabled Nametag Maker.";

    /**
     * The message sent when the plugin is enabled.
     */
    public static String enable = ChatColor.GREEN + "Enabled. " + ChatColor.GRAY + "This will be overridden by config.yml on reload";

    /**
     * The message sent to console when the plugin is enabled.
     */
    public static String enabled = ChatColor.YELLOW + " has enabled Nametag Maker.";

    /**
     * The message sent when attempting to enable the plugin and its already enabled.
     */
    public static String alreadyEnabled = "Already enabled. Use " + ChatColor.GREEN + "/nametag disable" + ChatColor.GRAY + " to disable.";

    /**
     * The message sent when starting to refresh nametags.
     */
    public static String refreshing = "Refreshing nametags...";

    /**
     * The message sent when finished refreshing nametags.
     */
    public static String refreshed = ChatColor.GREEN + "All nametags refreshed!";

    /**
     * The message sent when attempting to refresh whilst the plugin is disabled.
     */
    public static String refreshDisabled = ChatColor.RED + "Cannot refresh. Plugin disabled!";

    /**
     * The message sent when reloading the language and configuration files.
     */
    public static String reloading = "Reloading lang and config...";

    /**
     * The message sent when reload is complete.
     */
    public static String reloaded = ChatColor.GREEN + "Reloaded lang and configuration!";

    /**
     * The message sent when reload fails.
     */
    public static String reloadFailed = ChatColor.RED + "Reload failed. If this problem persists consider deleting config.yml and lang.yml to reset them.";

    /**
     * The message sent when a command is not found.
     */
    public static String unrecognized = ChatColor.RED + "Command not recognised.";

    /**
     * The message sent when mentioning /<MainCommand> help exists.
     */
    public static String useHelp = "Use" + ChatColor.GREEN + " /nametag help" + ChatColor.GRAY + " to see all commands.";

    /**
     * Initializes the language file for the plugin. It checks if the file exists and creates it if it needs to be. Then
     * it moves onto reading the language file.
     *
     * @return Whether initialization was a success or not.
     */
    public static boolean init () {
        try {
            if (!new File("./plugins/NametagMaker/lang.yml").exists()) plugin.saveResource("lang.yml", false);
            return read();
        }
        catch (Exception exception) {
            NametagMaker.console("Could not create directories.",  ChatColor.RED);
            NametagMaker.console(exception.toString(), ChatColor.RED);
            return false;
        }
    }

    /**
     * Reads the language file for the plugin. Hosted at plugins/'PluginName'/lang.yml
     *
     * @return Whether the reading the language was a success.
     */
    @SuppressWarnings("All")
    public static boolean read () {
        FileConfiguration langFile = new YamlConfiguration();
        try {
            langFile.load("./plugins/NametagMaker/lang.yml");
        } catch (Exception exception) {
            NametagMaker.console("Could not read file.",  ChatColor.RED);
            NametagMaker.console(exception.toString(), ChatColor.RED);
            return false;
        }
        prefix = langFile.getString("prefix", ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "N" + ChatColor.AQUA + "M" + ChatColor.DARK_GRAY + "] " + ChatColor.GRAY);
        try {
            Scanner s = new Scanner(new File("./plugins/NametagMaker/lang.yml"));
            while (s.hasNextLine()) {
                String line = s.nextLine().replace('&','§');
                if (line.contains("#")) continue;
                else if (line.contains("prefix: ")) prefix = line.replace("prefix: ", "");
                else if (line.contains("unrecognized: ")) unrecognized = line.replace("unrecognized: ", "");
                else if (line.contains("useHelp: ")) useHelp = line.replace("useHelp: ", "");
                else if (line.contains("alreadyDisabled: ")) alreadyDisabled = line.replace("alreadyDisabled: ", "");
                else if (line.contains("disable: ")) disable = line.replace("disable: ", "");
                else if (line.contains("reEnable: ")) reEnable = line.replace("reEnable: ", "");
                else if (line.contains("reDisable: ")) reDisable = line.replace("reDisable: ", "");
                else if (line.contains("noTag: ")) noTag = line.replace("noTag: ", "");
                else if (line.contains("tagNotFound: ")) tagNotFound = line.replace("tagNotFound: ", "");
                else if (line.contains("disabled: ")) disabled = line.replace("disabled: ", "");
                else if (line.contains("enable: ")) enable = line.replace("enable: ", "");
                else if (line.contains("enabled: ")) enabled = line.replace("enabled: ", "");
                else if (line.contains("alreadyEnabled: ")) alreadyEnabled = line.replace("alreadyEnabled: ", "");
                else if (line.contains("refreshing: ")) refreshing = line.replace("refreshing: ", "");
                else if (line.contains("refreshed: ")) refreshed = line.replace("refreshed: ", "");
                else if (line.contains("refreshDisabled: ")) refreshDisabled = line.replace("refreshDisabled: ", "");
                else if (line.contains("reloading: ")) reloading = line.replace("reloading: ", "");
                else if (line.contains("reloaded: ")) reloaded = line.replace("reloaded: ", "");
                else if (line.contains("reloadFailed: ")) reloadFailed = line.replace("reloadFailed: ", "");
            }
            return true;
        } catch (Exception exception) {
            NametagMaker.console("Could not read file.",  ChatColor.RED);
            NametagMaker.console(exception.toString(), ChatColor.RED);
            return false;
        }
    }

    /**
     * Calculates the header to send to players when running NametagMaker commands.
     *
     * @return The string version of the header to send to players.
     */
    public static String generateHeader () {
        String e;
        if (Config.enabled) e = ChatColor.GREEN + "Enabled";
        else e = ChatColor.RED + "Disabled";
        return Lang.prefix + ChatColor.GOLD + "Nametag " + ChatColor.AQUA + "Maker " + ChatColor.GRAY + NametagMaker.version + " - " + e;
    }
}
