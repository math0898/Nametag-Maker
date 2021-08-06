package io.github.math0898.nametagmaker;

import org.bukkit.ChatColor;

import java.util.ArrayList;

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
     * Default constructor for a TagGroup. Simply requires a name.
     *
     * @param name The name of the tag group.
     */
    public TagGroup (String name) {
        this.name = name;
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
    public void setPermission (String p) { //TODO
        permission = p;
    }

    /**
     * Sets the weight for the TagGroup.
     */
    public void setWeight (int i) { //TODO
        weight = i;
    }
}
