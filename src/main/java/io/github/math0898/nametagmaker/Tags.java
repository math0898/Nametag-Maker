package io.github.math0898.nametagmaker;

import org.bukkit.ChatColor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class handles the loading and creating of tags.yml which describe the tag groups used by the plugin.
 *
 * @author Sugaku
 */
public class Tags {

    /**
     * The ArrayList of TagGroups.
     */
    public static ArrayList<TagGroup> groups = new ArrayList<>();

    /**
     * Initializes the tags file for the plugin. It checks if the file exists and creates it if it needs to be. Then
     * it moves onto reading the tags file.
     *
     * @return Whether initialization was a success or not.
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static boolean init () {
        try {
            File container = new File("./plugins/NametagMaker/");
            if (!Files.exists(Paths.get(container.getPath()))) container.mkdir();
            File tags = new File("./plugins/NametagMaker/tags.yml");
            if (!Files.exists(Paths.get(tags.getPath()))) if (!create()) return false;
            return read();
        }
        catch (Exception exception) {
            NametagMaker.console("Could not create directories.",  ChatColor.RED);
            NametagMaker.console(exception.toString(), ChatColor.RED);
            return false;
        }
    }

    /**
     * Reads the tags file for the plugin. Hosted at plugins/'PluginName'/tags.yml
     *
     * @return Whether the reading the language was a success.
     */
    @SuppressWarnings("All")
    public static boolean read () {
        groups = new ArrayList<>();
        try {
            Scanner s = new Scanner(new File("./plugins/NametagMaker/tags.yml"));
            while (s.hasNextLine()) {
                String line = s.nextLine().replace('&','§');
                if (line.contains("#")) { } //Empty body if
                else if (line.contains("name: ")) groups.add(new TagGroup(line.replace("name: ", "")));
                else if (line.contains("player: ")) groups.get(groups.size() - 1).addPlayer(line.replace("player: ", ""));
                else if (line.contains("prefix: ")) groups.get(groups.size() - 1).setPrefix(line.replace("prefix: ", ""));
                else if (line.contains("suffix: ")) groups.get(groups.size() - 1).setSuffix(line.replace("suffix: ", ""));
                else if (line.contains("color: ")) groups.get(groups.size() - 1).setColor(line.replace("color: ", "").replace("§", "").replace("&", ""));
                else if (line.contains("permission: ")) groups.get(groups.size() - 1).setPermission(line.replace("permission: ", ""));
                else if (line.contains("weight: ")) groups.get(groups.size() - 1).setWeight(Integer.parseInt(line.replace("weight: ", "")));
                else if (line.contains("visible: ")) groups.get(groups.size() - 1).setVisible(Boolean.parseBoolean(line.replace("visible: ", "")));

            }
            return true;
        } catch (Exception exception) {
            NametagMaker.console("Could not read file.",  ChatColor.RED);
            NametagMaker.console(exception.toString(), ChatColor.RED);
            return false;
        }
    }

    /**
     * Creates the default language file for the plugin. Hosted at plugins/'PluginName'/tags.yml
     *
     * @return Whether creating the language file was a success.
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static boolean create () {
        try {
            File tags = new File("./plugins/NametagMaker/tags.yml");
            tags.delete();
            tags.createNewFile();
            FileWriter writer = new FileWriter(tags);
            writer.write("# This is the default tags file for the Nametag Maker plugin.\n");
            writer.write("# You can add lines to this file to add groups in the game.\n");
            writer.write("# Every group must start with a name:<name> tag and then can\n");
            writer.write("# include any number of the following optional tags listed bellow.\n");
            writer.write("# You may want to also look up the Minecraft color codes.\n");
            writer.write("# ---- Tags ----\n");
            writer.write("#\n");
            writer.write("# name: <name> - 'Declares' a new tag group.\n");
            writer.write("# player: <playerName> - Adds the player to the group regardless of\n");
            writer.write("#                        permissions. One player per however multiple\n");
            writer.write("#                        'player:' can exist per group.\n");
            writer.write("# prefix: <prefix> - Sets the prefix for this group.\n");
            writer.write("# suffix: <suffix> - Sets the suffix for this group.\n");
            writer.write("# color: <colorCode> - Sets the color for the group. Please use chat color\n");
            writer.write("#                      codes such as &a = Green.\n");
            writer.write("# permission: <node> - If present players with this node will be added to the\n");
            writer.write("#                      group.\n");
            writer.write("# weight: <int> - The weight value of the tag. The higher the weight the more\n");
            writer.write("#                 it overrides others.\n");
            writer.write("# visible: <true/false> - Does nothing if set to true. If set to false nametags\n");
            writer.write("#                         will not be visible above player heads.\n");
            writer.write("name: default\n");
            writer.write("color: &a\n");
            writer.write("permission: nametag.default\n");
            writer.close();
            return true;
        } catch (Exception exception) {
            NametagMaker.console("Could not create default file.",  ChatColor.RED);
            NametagMaker.console(exception.getMessage(), ChatColor.RED);
            return false;
        }
    }

    /**
     * Returns the TagGroup if any of them match the given name.
     *
     * @param n The string name of the requested team.
     */
    public static TagGroup findTeam (String n) {
        for (TagGroup g: groups) if (g.name.equals(n.replace("nt-", ""))) return g;
        return null;
    }

    /**
     * Saves all TagGroups currently loaded to the tags.yml file.
     */
    public static void save () {
        File f = new File("./plugins/NametagMaker/tags.yml");
        try {
            //noinspection ResultOfMethodCallIgnored
            f.delete();
            FileWriter writer = new FileWriter(f);
            writer.write("# This is the default tags file for the Nametag Maker plugin.\n");
            writer.write("# You can add lines to this file to add groups in the game.\n");
            writer.write("# Every group must start with a name:<name> tag and then can\n");
            writer.write("# include any number of the following optional tags listed bellow.\n");
            writer.write("# You may want to also look up the Minecraft color codes.\n");
            writer.write("# ---- Tags ----\n");
            writer.write("#\n");
            writer.write("# name: <name> - 'Declares' a new tag group.\n");
            writer.write("# player: <playerName> - Adds the player to the group regardless of\n");
            writer.write("#                        permissions. One player per however multiple\n");
            writer.write("#                        'player:' can exist per group.\n");
            writer.write("# prefix: <prefix> - Sets the prefix for this group.\n");
            writer.write("# suffix: <suffix> - Sets the suffix for this group.\n");
            writer.write("# color: <colorCode> - Sets the color for the group. Please use chat color\n");
            writer.write("#                      codes such as &a = Green.\n");
            writer.write("# permission: <node> - If present players with this node will be added to the\n");
            writer.write("#                      group.\n");
            writer.write("# weight: <int> - The weight value of the tag. The higher the weight the more\n");
            writer.write("#                 it overrides others.\n");
            writer.write("# visible: <true/false> - Does nothing if set to true. If set to false nametags\n");
            writer.write("#                         will not be visible above player heads.\n");
            for (TagGroup t: groups) {
                writer.write("name: " + t.name + "\n");
                if (t.color != null) writer.write("color: " + t.color.toString().replace("§", "&") + "\n");
                else writer.write("color: &f \n");
                writer.write("prefix: " + t.prefix + "\n");
                writer.write("suffix: " + t.suffix + "\n");
                writer.write("permission: " + t.permission + "\n");
                writer.write("weight: " + t.weight + "\n");
                writer.write("visible: " + t.visible + "\n");
                for (String n: t.players) writer.write("player: " + n + "\n");
            }
            writer.close();
        } catch (IOException exception) {
            NametagMaker.console("Could not save tags.", ChatColor.RED);
            NametagMaker.console(exception.getMessage(), ChatColor.RED);
        }
    }

    /**
     * Used to compute and access a list of all the names of tags.
     *
     * @return The full list of tag names.
     */
    public static List<String> getTagNames () {
        ArrayList<String> toReturn = new ArrayList<>();
        for (TagGroup g: groups) toReturn.add(g.name);
        return toReturn;
    }
}
