package io.github.math0898.nametagmaker.commands;

import io.github.math0898.nametagmaker.TagGroup;
import io.github.math0898.nametagmaker.Tags;
import io.github.math0898.nametagmaker.commands.subcommands.*;
import io.github.math0898.nametagmaker.lang.Lang;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;

import java.util.*;

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
     * A map of subcommands indexed by the first argument to invoke them.
     */
    private final Map<String, Subcommand> subcommands = new HashMap<>();

    /**
     * Creates the main nametagmaker command.
     */
    public MainCommand () { // TODO: Could a design pattern be used here?
        subcommands.put("info", new InfoSubcommand());
        subcommands.put("create", new CreateSubcommand());
        subcommands.put("help", new HelpSubcommand());
        subcommands.put("status", new StatusSubcommand());
        subcommands.put("disable", new DisableSubcommand());
        subcommands.put("edit", new EditSubcommand());
        subcommands.put("enable", new EnableSubcommand());
        subcommands.put("refresh", new RefreshSubcommand());
        subcommands.put("reload", new ReloadSubcommand());
    }

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
    public boolean onCommand (@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (args.length == 0) subcommands.get("status").execute(sender, args);
        else {
            Subcommand s = subcommands.get(args[0].toLowerCase());
            if (s != null) return s.execute(sender, args);
            else sender.sendMessage(Lang.prefix + Lang.unrecognized);
        }
        return true;
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
    public List<String> onTabComplete (CommandSender sender, Command command, String alias, String[] args) {
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
            list.sort(String::compareTo);
            if (!args[args.length - 1].equals("")) list.removeIf(o -> !o.toLowerCase().startsWith(args[args.length - 1].toLowerCase()));
            return list;
        }
        return null;
    }
}
