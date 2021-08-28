package io.github.math0898.nametagmaker;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a group given a particular tag. Contains useful information such as the group's name, prefixes, suffixes,
 * color, permission nodes, weight, and more.
 *
 * @author Sugaku
 */
public class TagGroup {

    /**
     * The name of the tag. Must be declared as it's how the tag is defined in the game.
     */
    public String name;

    /**
     * The color of the TagGroup.
     */
    public ChatColor color = ChatColor.WHITE;

    /**
     * A list of player names which should be assigned this group when they join.
     */
    public ArrayList<String> players = new ArrayList<>();

    /**
     * The prefix for this particular TagGroup.
     */
    public String prefix = "";

    /**
     * The suffix for this particular TagGroup.
     */
    public String suffix = "";

    /**
     * The permission node for this particular TagGroup.
     */
    public String permission = null;

    /**
     * The weight for this particular TagGroup.
     */
    public int weight = 0;

    /**
     * Whether this tag is visible.
     */
    public boolean visible = true;

    /**
     * Default constructor for a TagGroup. Simply requires a name.
     *
     * @param name The name of the tag group.
     */
    public TagGroup (String name) {
        this.name = name;
    }

    /**
     * The, 'I know all the values I want' constructor for a TagGroup. Params are nullable and are left as their default
     * value if done so.
     *
     * @param name The name of the tag group.
     * @param color The color of the tag group.
     * @param players The players assigned the tag group.
     * @param prefix The prefix of the tag group.
     * @param suffix The suffix of the tag group.
     * @param permission The permission node for the tag group.
     * @param weight The weight of the tag group.
     * @param visible Whether the tag is visible.
     */
    public TagGroup (String name, String color, ArrayList<String> players, String prefix, String suffix,
                     String permission, int weight, boolean visible) {
        this.name = name;
        if (color != null) this.setColor(color);
        if (players != null) this.players = players;
        if (prefix != null) this.prefix = prefix;
        if (suffix != null) this.suffix = suffix;
        if (permission != null) this.permission = permission;
        this.weight = weight;
        this.visible = visible;
    }

    /**
     * Adds the player to the TagGroup.
     */
    public void addPlayer (String p) {
        players.add(p);
    }

    /**
     * Sets the color of the TagGroup.
     */
    public void setColor (String c) {
        color = ChatColor.getByChar(c.charAt(0));
    }

    /**
     * Sets the prefix for this TagGroup.
     */
    public void setPrefix (String p) {
        prefix = p;
    }

    /**
     * Sets the suffix for this TagGroup.
     */
    public void setSuffix (String p) {
        suffix = p;
    }

    /**
     * Sets the permission node for the TagGroup.
     */
    public void setPermission (String p) {
        permission = p;
    }

    /**
     * Sets the weight for the TagGroup.
     */
    public void setWeight (int i) {
        weight = i;
    }

    /**
     * Sets the visibility of the tag.
     */
    public void setVisible (boolean b) {
        visible = b;
    }

    /**
     * Parses a tag from the create-tag subcommand and then sends it off to be saved in tags.yml
     *
     * @param input The string input being parsed.
     * @return What should be sent to the player.
     */
    public static TagGroup parseTag (String input) {
        if (!input.contains("name:")) return null;
        String name = "";
        String permission = null;
        String prefix = null;
        String suffix = null;
        String color = ChatColor.WHITE.toString();
        boolean visible = true;
        int weight = 0;
        Scanner s = new Scanner(input);
        while (s.hasNext()) {
            String read = s.next();
            if (read.contains("name:")) name = read.replace("name:", "");
            else if (read.contains("prefix:") || read.contains("suffix:")) {
                String temp = read;
                if (temp.length() - temp.replace("\"", "").length() != 2){
                    read = read.replace("\"", "");
                    while (!read.contains("\"") && s.hasNext()) {
                        read = s.next();
                        temp += " " + read;
                    }
                }
                if (temp.contains("prefix:")) prefix = temp.replace("\"", "").replace("prefix:", "");
                else if (temp.contains("suffix:")) suffix = temp.replace("\"", "").replace("suffix:", "");
            } else if (read.contains("color:")) color = read.replace("color:&", "");
            else if (read.contains("permission:")) permission = read.replace("permission:", "");
            else if (read.contains("visible:")) visible = Boolean.parseBoolean(read.replace("visible:", ""));
            else if (read.contains("weight:")) weight = Integer.parseInt(read.replace("weight:", ""));
        }
        return new TagGroup(name, color, null, prefix, suffix, permission, weight, visible);
    }
}
