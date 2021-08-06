package io.github.math0898.nametagmaker;

import org.bukkit.ChatColor;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * This class handles the configuration for this plugin. It's used to read what admins have changed and write the
 * default configurations.
 *
 * @author Sugaku
 */
public class Config {

    /**
     * A boolean which states whether the plugin is enabled.
     */
    public static boolean enabled = false;

    /**
     * Initializes the configuration for the plugin. It checks if the file exists and creates it if it needs to be. Then
     * it moves onto reading the configuration.
     *
     * @return Whether initialization was a success or not.
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static boolean init() {
        try {
            File container = new File("./plugins/NametagMaker/");
            if (!Files.exists(Paths.get(container.getPath()))) container.mkdir();
            File config = new File("./plugins/NametagMaker/config.yml");
            if (!Files.exists(Paths.get(config.getPath()))) if (!create()) return false;
            return read();
        }
        catch (Exception exception){
            main.console("Could not create directories.",  ChatColor.RED);
            main.console(exception.toString(), ChatColor.RED);
            return false;
        }
    }

    /**
     * Reads the configuration for the plugin. Hosted at plugins/'PluginName'/config.yml
     *
     * @return Whether reading the config was a success or not.
     */
    public static boolean read () {
        try {
            Scanner s = new Scanner(new File("./plugins/NametagMaker/config.yml"));
            while (s.hasNextLine()) {
                String line = s.nextLine();
                if (line.contains("#")) continue;
                if (line.contains("enabled: ")) {
                    Scanner s2 = new Scanner(line);
                    s2.next();
                    enabled = Boolean.parseBoolean(s2.next());
                }
            }
            return true;
        } catch (Exception exception) {
            main.console("Could not read file.",  ChatColor.RED);
            main.console(exception.toString(), ChatColor.RED);
            return false;
        }
    }

    /**
     * Creates the default configuration for the plugin. Hosted at plugins/'PluginName'/config.yml
     *
     * @return Whether creating the config was a success or not.
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static boolean create () {
        try {
            File config = new File("./plugins/NametagMaker/config.yml");
            config.delete();
            config.createNewFile();
            FileWriter writer = new FileWriter(config);
            writer.write("# This is the default configuration file for the Nametag Maker plugin which is tested on a 1.17.1 paper\n");
            writer.write("# server for functionality. It was inspired by Nametagedit and aims to provide all the functionality of\n");
            writer.write("# the abandoned project and some additional features. Please report bugs and request updates on github.\n");
            writer.write("# Github: https://www.github.com/math0898/Nametag-Maker \n");
            writer.write("# Donations: https://www.paypal.com/biz/fund?id=MB84KE9Z6NDDC \n");
            writer.write("#\n");
            writer.write("#\n");
            writer.write("# ---- General ----\n");
            writer.write("# Is the plugin enabled? (true/false)\n");
            writer.write("enabled: true\n");
            writer.close();
            return true;
        } catch (Exception exception) {
            main.console("Could not create default file.",  ChatColor.RED);
            main.console(exception.getMessage(), ChatColor.RED);
            return false;
        }
    }
}
