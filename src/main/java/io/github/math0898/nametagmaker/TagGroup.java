package io.github.math0898.nametagmaker;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

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
    public ChatColor color = null;

    /**
     * A list of player names which should be assigned this group when they join.
     */
    public ArrayList<String> players = new ArrayList<>();

    /**
     * The prefix for this particular TagGroup.
     */
    public String prefix = null;

    /**
     * The suffix for this particular TagGroup.
     */
    public String suffix = null;

    /**
     * The permission node for this particular TagGroup.
     */
    public String permission = null;

    /**
     * The weight for this particular TagGroup.
     */
    public Integer weight = null;

    /**
     * Whether this tag is visible.
     */
    public Boolean visible = null;

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
                     String permission, Integer weight, Boolean visible) {
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
        Boolean visible = null;
        Integer weight = null;
        Scanner s = new Scanner(input);
        while (s.hasNext()) {
            String read = s.next();
            if (read.contains("name:")) name = read.replace("name:", "");
            else if (read.contains("prefix:")) prefix = parseQuotations(s, read);
            else if (read.contains("suffix:")) suffix = parseQuotations(s, read);
            else if (read.contains("color:")) color = read.replace("color:&", "");
            else if (read.contains("permission:")) permission = read.replace("permission:", "");
            else if (read.contains("visible:")) visible = Boolean.parseBoolean(read.replace("visible:", ""));
            else if (read.contains("weight:")) weight = Integer.parseInt(read.replace("weight:", ""));
        }
        return new TagGroup(name, color, null, prefix, suffix, permission, weight, visible);
    }

    /**
     * A private helper method to turn a scanner into a suffix or prefix.
     *
     * @param scanner The scanner which holds the rest of the string.
     * @param read The small section that was read to get to this point.
     * @return The string which describes either the suffix or prefix.
     */
    private static String parseQuotations (Scanner scanner, String read) {
        StringBuilder temp = new StringBuilder(read.replace("prefix:", "")
                                                   .replace("suffix:", "").replace("\"",""));
        if (temp.length() - temp.toString().replace("\"", "").length() != 2){
            read = read.replace("\"", "");
            while (!read.contains("\"") && scanner.hasNext()) {
                read = scanner.next();
                temp.append(" ").append(read.replace("\"", ""));
            }
        }
        return temp.toString();
    }

    /**
     * Updates this group with the information of another group given that it is both different and not a default value.
     *
     * @param mod The group which information is pulled from to update this group.
     */
    public void sync (TagGroup mod) {
        if (mod == null) return;
        if (!mod.name.equals(name)) name = mod.name;
        if (mod.visible != null) visible = mod.visible;
        if (mod.weight != null) weight = mod.weight;
        if (mod.permission != null) permission = mod.permission;
        if (mod.suffix != null) suffix = mod.suffix;
        if (mod.prefix != null) prefix = mod.prefix;
        if (mod.color != null) color = mod.color;
    }


    /**
     * Fancy prints this tag to the given player. // TODO: This violates separation of concerns. Perhaps do a toFancyString().
     *
     * @param sender The person to send the fancy version to.
     */
    public void fancyPrint (CommandSender sender) {
        sender.sendMessage(ChatColor.GRAY + "> name: " + ChatColor.GOLD + name);
        sender.sendMessage(ChatColor.GRAY + "> prefix: \"" + prefix + ChatColor.GRAY + "\"");
        sender.sendMessage(ChatColor.GRAY + "> suffix: \"" + suffix + ChatColor.GRAY + "\"");
        sender.sendMessage(ChatColor.GRAY + "> color: " + color + "&" + color.getChar());
        sender.sendMessage(ChatColor.GRAY + "> permission: " + ChatColor.LIGHT_PURPLE + permission);
        if (visible) sender.sendMessage(ChatColor.GRAY + "> visible: " + ChatColor.GREEN + "true");
        else sender.sendMessage(ChatColor.GRAY + "> visible: " + ChatColor.RED + "false");
        sender.sendMessage(ChatColor.GRAY + "> weight: " + ChatColor.BLUE + weight);
        if (players.size() > 0) {
            sender.sendMessage(ChatColor.GRAY + "> players: ");
            for (String s: players) sender.sendMessage(ChatColor.GRAY + "  > " + ChatColor.AQUA + s);
        }
        sender.sendMessage(ChatColor.GRAY + "> preview: " + prefix + color + sender.getName() + suffix);
    }
}
