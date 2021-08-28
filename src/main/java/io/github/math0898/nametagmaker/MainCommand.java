package io.github.math0898.nametagmaker;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * This is the main command for interacting with the plugin as a whole.
 *
 * @author Sugaku
 */
public class MainCommand implements CommandExecutor {

    /**
     * The tab completer which fills in options for the command as the player types.
     */
    public static TabCompleter autocomplete = new AutocompleteMainCommand();

    /**
     * Executes the main command behind the Nametag Maker plugin.
     *
     * @param sender  Source of the command.
     * @param command Command which was executed.
     * @param label   Alias of the command which was used.
     * @param args    Passed command arguments.
     * @return true if a valid command, otherwise false.
     */
    @Override
    public boolean onCommand (CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) usage(sender);
        else if (args[0].equalsIgnoreCase("create")) createSubcommand(sender, args);
        else if (args[0].equalsIgnoreCase("disable")) disableSubcommand(sender);
        else if (args[0].equalsIgnoreCase("edit")) editSubcommand(sender, args);
        else if (args[0].equalsIgnoreCase("enable")) enableSubcommand(sender);
        else if (args[0].equalsIgnoreCase("help")) helpSubcommand(sender);
        else if (args[0].equalsIgnoreCase("info")) infoSubcommand(sender, args);
        else if (args[0].equalsIgnoreCase("refresh")) refreshSubcommand(sender);
        else if (args[0].equalsIgnoreCase("reload")) reloadSubcommand(sender);
        else sender.sendMessage(Lang.prefix + Lang.unrecognized);
        return true;
    }

    /**
     * Sends usage information to the given command sender.
     *
     * @param sender Source of the command.
     */
    private void usage (CommandSender sender) {
        String e;
        if (Config.enabled) e = ChatColor.GREEN + "Enabled";
        else e = ChatColor.RED + "Disabled";
        sender.sendMessage(Lang.prefix + ChatColor.GOLD + "Nametag " + ChatColor.AQUA + "Maker " + ChatColor.GRAY + main.version + " - " + e);
        sender.sendMessage(Lang.prefix + Lang.useHelp);
    }

    /**
     * Creates a new tag which is then saved at tags.yml.
     *
     * @param sender Source of the command.
     * @param args The arguments of the command.
     */
    private void createSubcommand (CommandSender sender, String[] args) {
        if (args.length < 2) {
            sender.sendMessage(Lang.prefix + ChatColor.GOLD + "Nametag " + ChatColor.AQUA + "Maker " + ChatColor.GRAY + " - (create)");
            sender.sendMessage(Lang.prefix + "This is hard to explain in game. Here's a link to the wiki article.");
            sender.sendMessage("https://github.com/math0898/Nametag-Maker/wiki/Commandline-Editor");
            return;
        }
        StringBuilder input = new StringBuilder();
        for (int i = 1; i < args.length; i++) input.append(" ").append(args[i]);
        TagGroup tag = TagGroup.parseTag(input.toString());
        if (tag == null) {
            sender.sendMessage(Lang.prefix + ChatColor.RED + "You " + ChatColor.UNDERLINE + "MUST" + ChatColor.RESET + ChatColor.RED + " specify a name with name:<name>");
            return;
        }
        Tags.groups.add(tag);
        Tags.save();
        NametagApplier.clean();
        NametagApplier.init();
        NametagApplier.refresh();
        sender.sendMessage(Lang.prefix + "Created new group " + ChatColor.GOLD + tag.name + ChatColor.GRAY + ".");
    }

    /**
     * Disables the plugin so that none of the functionality works other than this command. This only applies for to
     * runtime and will be overridden with config.yml on reload or restart.
     *
     * @param sender Source of the command.
     */
    private void disableSubcommand (CommandSender sender) {
        if (Config.enabled) {
            NametagApplier.clean();
            Config.enabled = false;
            sender.sendMessage(Lang.prefix + Lang.disable);
            sender.sendMessage(Lang.prefix + Lang.reEnable);
            Bukkit.getConsoleSender().sendMessage(Lang.prefix + sender.getName() + Lang.disabled);
        } else sender.sendMessage(Lang.prefix + Lang.alreadyDisabled);
    }

    /**
     * Edits a given tag allowing the modification and addition of new values.
     *
     * @param sender Source of the command.
     * @param args The arguments of the command.
     */
    private void editSubcommand (CommandSender sender, String[] args) {
        //TODO: Implement
    }

    /**
     * Enables the plugin so that the functionality of it will work again. This only applies for runtime and will be
     * overridden with config.yml on reload or restart.
     *
     * @param sender Source of the command.
     */
    private void enableSubcommand (CommandSender sender) {
        if (!Config.enabled) {
            Config.enabled = true;
            NametagApplier.init();
            NametagApplier.refresh();
            sender.sendMessage(Lang.prefix + Lang.enable);
            sender.sendMessage(Lang.prefix + Lang.reDisable);
            Bukkit.getConsoleSender().sendMessage(Lang.prefix + sender.getName() + Lang.enabled);
        } else sender.sendMessage(Lang.prefix + Lang.alreadyEnabled);
    }

    /**
     * Sends help information to the given command sender.
     *
     * @param sender Source of the command.
     */
    private void helpSubcommand (CommandSender sender) {
        String e;
        if (Config.enabled) e = ChatColor.GREEN + "Enabled";
        else e = ChatColor.RED + "Disabled";
        sender.sendMessage(Lang.prefix + ChatColor.GOLD + "Nametag " + ChatColor.AQUA + "Maker " + ChatColor.GRAY + main.version + " - " + e);
        sender.sendMessage(ChatColor.GRAY + "> " + ChatColor.GREEN + "/ntm create <name> <args>");
        sender.sendMessage(ChatColor.GRAY + "> " + ChatColor.GREEN + "/ntm disable");
        sender.sendMessage(ChatColor.GRAY + "> " + ChatColor.GREEN + "/ntm edit <tag> <args>");
        sender.sendMessage(ChatColor.GRAY + "> " + ChatColor.GREEN + "/ntm enable");
        sender.sendMessage(ChatColor.GRAY + "> " + ChatColor.GREEN + "/ntm help");
        sender.sendMessage(ChatColor.GRAY + "> " + ChatColor.GREEN + "/ntm info <tag>");
        sender.sendMessage(ChatColor.GRAY + "> " + ChatColor.GREEN + "/ntm refresh");
        sender.sendMessage(ChatColor.GRAY + "> " + ChatColor.GREEN + "/ntm reload");
    }

    /**
     * Sends all the information of the given tag to the command sender.
     *
     * @param sender Source of the command.
     * @param args The arguments of the command.
     */
    private void infoSubcommand (CommandSender sender, String[] args) {
        if (args.length < 2) {
            sender.sendMessage(Lang.prefix + Lang.noTag);
            return;
        }
        TagGroup g = null;
        for (TagGroup t: Tags.groups) if (args[1].equalsIgnoreCase(t.name)) {
            g = t;
            break;
        }
        if (g == null) {
            sender.sendMessage(Lang.prefix + Lang.tagNotFound);
            return;
        }
        String e;
        if (Config.enabled) e = ChatColor.GREEN + "Enabled";
        else e = ChatColor.RED + "Disabled";
        sender.sendMessage(Lang.prefix + ChatColor.GOLD + "Nametag " + ChatColor.AQUA + "Maker " + ChatColor.GRAY + main.version + " - " + e);
        sender.sendMessage(ChatColor.GRAY + "> name: " + ChatColor.GOLD + g.name);
        sender.sendMessage(ChatColor.GRAY + "> prefix: \"" + g.prefix + ChatColor.GRAY + "\"");
        sender.sendMessage(ChatColor.GRAY + "> suffix: \"" + g.suffix + ChatColor.GRAY + "\"");
        sender.sendMessage(ChatColor.GRAY + "> color: " + g.color + "&" + g.color.getChar());
        sender.sendMessage(ChatColor.GRAY + "> permission: " + ChatColor.LIGHT_PURPLE + g.permission);
        if (g.visible) sender.sendMessage(ChatColor.GRAY + "> visible: " + ChatColor.GREEN + "true");
        else sender.sendMessage(ChatColor.GRAY + "> visible: " + ChatColor.RED + "false");
        sender.sendMessage(ChatColor.GRAY + "> weight: " + ChatColor.BLUE + g.weight);
        if (g.players.size() > 0) {
            sender.sendMessage(ChatColor.GRAY + "> players: ");
            for (String s: g.players) sender.sendMessage(ChatColor.GRAY + "  > " + ChatColor.AQUA + s);
        }
        sender.sendMessage(ChatColor.GRAY + "> preview: " + g.prefix + g.color + sender.getName() + g.suffix);
    }

    /**
     * Refreshes the nametags of all players currently on the server.
     *
     * @param sender Sender of the command.
     */
    private void refreshSubcommand (CommandSender sender) {
        if (Config.enabled) {
            sender.sendMessage(Lang.prefix + Lang.refreshing);
            NametagApplier.refresh();
            sender.sendMessage(Lang.prefix + Lang.refreshed);
        } else {
            sender.sendMessage(Lang.prefix + Lang.refreshDisabled);
            sender.sendMessage(Lang.prefix + Lang.reEnable);
        }
    }

    /**
     * Reloads the configuration and lang files for the server.
     *
     * @param sender Source of the command.
     */
    private void reloadSubcommand (CommandSender sender) {
        sender.sendMessage(Lang.prefix + Lang.reloading);
        long start = System.currentTimeMillis();
        if (Config.read() && Lang.read()) {
            NametagApplier.clean();
            NametagApplier.init();
            NametagApplier.refresh();
            sender.sendMessage(Lang.prefix + Lang.reloaded);
            sender.sendMessage(Lang.prefix + "Took: " + (System.currentTimeMillis() - start) + "ms");
        } else sender.sendMessage(Lang.prefix + Lang.reloadFailed);
    }
}

/**
 * This class handles the tab completion of the command described above.
 *
 * @author Sugaku
 */
class AutocompleteMainCommand implements TabCompleter {

    /**
     * Requests a list of possible completions for a command argument.
     *
     * @param sender  Source of the command.  For players tab-completing a
     *                command inside a command block, this will be the player, not
     *                the command block.
     * @param command Command which was executed.
     * @param alias   The alias used.
     * @param args    The arguments passed to the command, including final
     *                partial argument to be completed and command label.
     * @return A List of possible completions for the final argument, or null
     * to default to the command executor.
     */
    @Override
    @SuppressWarnings("All")
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (command.getName().equalsIgnoreCase("nametag")) {
            ArrayList<String> list = new ArrayList<>();
            if (args.length == 1) list.addAll(Arrays.asList("create", "disable", "edit", "enable", "help", "info", "refresh", "reload"));
            else if (args.length == 2) {
                if (args[0].equalsIgnoreCase("create")) list.add("name:");
                else if (args[0].equalsIgnoreCase("edit") || args[0].equalsIgnoreCase("info")) {
                    for (TagGroup g: Tags.groups) list.add(g.name);
                    list.sort(String::compareTo);
                }
            } else if (args.length >= 3) {
                String check = "";
                for (String u: args) check += u;
                if ((check.length() - check.replace("\"", "").length()) % 2 != 0) return list;
                if (!check.contains("color:")) list.add("color:");
                if (!check.contains("permission:")) list.add("permission:");
                if (!check.contains("prefix:\"")) list.add("prefix:\"");
                if (!check.contains("suffix:\"")) list.add("suffix:\"");
                if (!check.contains("visible:")) list.add("visible:");
                if (!check.contains("weight:")) list.add("weight:");
                if (args[0].equalsIgnoreCase("edit")) if (!check.contains("name:")) list.add("name:");
            }
            if (!args[args.length - 1].equals("")) list.removeIf(o -> !o.toLowerCase().startsWith(args[args.length - 1].toLowerCase()));
            return list;
        }
        return null;
    }
}