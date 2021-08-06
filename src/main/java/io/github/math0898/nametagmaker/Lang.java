package io.github.math0898.nametagmaker;

import org.bukkit.ChatColor;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * This class handles all the messages which are sent to players and admins. This is placed here for easy access for
 * translations and configuring the plugin to match server branding.
 *
 * @author Sugaku
 */
public class Lang {

    /**
     * The main prefix used for any messages sent during runtime.
     */
    public static String prefix =  ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "N" + ChatColor.AQUA + "M" + ChatColor.DARK_GRAY + "] " + ChatColor.GRAY;

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
     * The message sent to console when the plugin is disabled.
     */
    public static String disabled = ChatColor.YELLOW + " has disabled Nametag Maker.";

    /**
     * The message sent when the plugin is enabled.
     */
    public static String enable = ChatColor.GREEN + "Enabled. " + ChatColor.GRAY + "This will be overridden by config.yml on reload";

    /**
     * The message sent on how to re-disable the plugin.
     */
    public static String reDisable = "Use " + ChatColor.GREEN + "/nametag disable" + ChatColor.GRAY + " to disable without a reload.";

    /**
     * The message sent to console when the plugin is enabled.
     */
    public static String enabled = ChatColor.YELLOW + " has enabled Nametag Maker.";

    /**
     * The message sent when attempting to enable the plugin and its already enabled.
     */
    public static String alreadyEnabled = "Already enabled. Use " + ChatColor.GREEN + "/nametag disable" + ChatColor.GRAY + " to disable.";

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
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static boolean init() {
        try {
            File container = new File("./plugins/NametagMaker/");
            if (!Files.exists(Paths.get(container.getPath()))) container.mkdir();
            File lang = new File("./plugins/NametagMaker/lang.yml");
            if (!Files.exists(Paths.get(lang.getPath()))) if (!create()) return false;
            return read();
        }
        catch (Exception exception) {
            main.console("Could not create directories.",  ChatColor.RED);
            main.console(exception.toString(), ChatColor.RED);
            return false;
        }
    }

    /**
     * Reads the language file for the plugin. Hosted at plugins/'PluginName'/messages.yml
     *
     * @return Whether the reading the language was a success.
     */
    @SuppressWarnings("All")
    public static boolean read() {
        try {
            Scanner s = new Scanner(new File("./plugins/NametagMaker/lang.yml"));
            while (s.hasNextLine()) {
                String line = s.nextLine();
                if (line.contains("#")) { } //Empty body if
                else if (line.contains("prefix: ")) prefix = line.replace("prefix: ", "");
                else if (line.contains("unrecognized: ")) unrecognized = line.replace("unrecognized: ", "");
                else if (line.contains("useHelp: ")) useHelp = line.replace("useHelp: ", "");
                else if (line.contains("alreadyDisabled: ")) alreadyDisabled = line.replace("alreadyDisabled: ", "");
                else if (line.contains("disable: ")) disable = line.replace("disable: ", "");
                else if (line.contains("reEnable: ")) reEnable = line.replace("reEnable: ", "");
                else if (line.contains("disabled: ")) disabled = line.replace("disabled: ", "");
                else if (line.contains("enable: ")) enable = line.replace("enable: ", "");
                else if (line.contains("reDisable: ")) reDisable = line.replace("reDisable: ", "");
                else if (line.contains("enabled: ")) enabled = line.replace("enabled: ", "");
                else if (line.contains("alreadyEnabled: ")) alreadyEnabled = line.replace("alreadyEnabled: ", "");
                else if (line.contains("reloading: ")) reloading = line.replace("reloading: ", "");
                else if (line.contains("reloaded: ")) reloaded = line.replace("reloaded: ", "");
            }
            return true;
        } catch (Exception exception) {
            main.console("Could not read file.",  ChatColor.RED);
            main.console(exception.toString(), ChatColor.RED);
            return false;
        }
    }

    /**
     * Creates the default language file for the plugin. Hosted at plugins/'PluginName'/messages.yml
     *
     * @return Whether creating the language file was a success.
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static boolean create() {
        try {
            File config = new File("./plugins/NametagMaker/lang.yml");
            config.delete();
            config.createNewFile();
            FileWriter writer = new FileWriter(config);
            writer.write("# This is the default lang file for the Nametag Maker plugin.\n");
            writer.write("#\n");
            writer.write("#\n");
            writer.write("# ---- General ----\n");
            writer.write("# Message Prefix\n");
            writer.write("prefix: " + prefix +"\n");
            writer.write("# ---- Command ----\n");
            writer.write("# The message sent when a command is not found\n");
            writer.write("unrecognized: " + unrecognized + "\n");
            writer.write("# The message sent when mentioning /<MainCommand> help exists.\n");
            writer.write("useHelp: " + useHelp + "\n");
            writer.write("# -- Disable Subcommand --\n");
            writer.write("# The message sent when the plugin is already disabled.\n");
            writer.write("alreadyDisabled: " + alreadyDisabled + "\n");
            writer.write("# The message sent when disabling the plugin is successful.\n");
            writer.write("disable: " + disable + "\n");
            writer.write("# The message sent on how to re-enable the plugin.\n");
            writer.write("reEnable: " + reEnable + "\n");
            writer.write("# The message sent to console when the plugin is disabled.\n");
            writer.write("disabled: " + disabled + "\n");
            writer.write("# -- Enable Subcommand --\n");
            writer.write("# The message sent when the plugin is enabled.\n");
            writer.write("enable: " + enable + "\n");
            writer.write("# The message sent on how to re-disable the plugin.\n");
            writer.write("reDisable: " + reDisable + "\n");
            writer.write("# The message sent to console when the plugin is enabled.\n");
            writer.write("enabled: " + enabled + "\n");
            writer.write("# The message sent when attempting to enable the plugin and its already enabled.\n");
            writer.write("alreadyEnabled: " + alreadyEnabled + "\n");
            writer.write("# -- Reload Subcommand --\n");
            writer.write("# The message sent when reloading the language and configuration files.\n");
            writer.write("reloading: " + reloading + "\n");
            writer.write("# The message sent when reload is complete.\n");
            writer.write("reloaded: " + reloaded + "\n");
            writer.close();
            return true;
        } catch (Exception exception) {
            main.console("Could not create default file.",  ChatColor.RED);
            main.console(exception.getMessage(), ChatColor.RED);
            return false;
        }
    }
}
